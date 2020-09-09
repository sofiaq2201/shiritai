package com.sofiaq.shiritai.security.controller;

import com.sofiaq.shiritai.dto.Message;
import com.sofiaq.shiritai.security.dto.JwtDto;
import com.sofiaq.shiritai.security.dto.LoginUserDto;
import com.sofiaq.shiritai.security.dto.NewUserDto;
import com.sofiaq.shiritai.security.enums.ERol;
import com.sofiaq.shiritai.security.jwt.JwtProvider;
import com.sofiaq.shiritai.security.model.Rol;
import com.sofiaq.shiritai.security.model.User;
import com.sofiaq.shiritai.security.service.RolService;
import com.sofiaq.shiritai.security.service.UserService;
import java.util.HashSet;
import java.util.Set;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserService userService;
    @Autowired
    RolService rolService;
    @Autowired
    JwtProvider jwtProvider;
    
    @PostMapping("/new")
    public ResponseEntity<?> newUser(@Valid @RequestBody NewUserDto newUser, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Message("Campos incorrectos o email invalido"), HttpStatus.BAD_REQUEST);
        if(userService.existsByUsername(newUser.getUsername()))
            return new ResponseEntity(new Message("Ese usuario ya existe"), HttpStatus.BAD_REQUEST);
        if(userService.existsByEmail(newUser.getEmail()))
            return new ResponseEntity(new Message("Ese email ya existe"), HttpStatus.BAD_REQUEST);
        //Create user
        User user = new User(newUser.getUsername(), newUser.getEmail(), passwordEncoder.encode(newUser.getPassword()));
        Set<Rol> roles = new HashSet();
        roles.add(rolService.getByNombre(ERol.ROLE_USER).get());
        if(newUser.getRoles().contains("admin"))
            roles.add(rolService.getByNombre(ERol.ROLE_ADMIN).get());
        user.setRoles(roles);
        userService.saveUser(user);

        return new ResponseEntity(new Message("Se creo con exito un nuevo usuario"), HttpStatus.CREATED);

    }

    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUserDto loginUserDto, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Message("Campos incorrectos"), HttpStatus.BAD_REQUEST);
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUserDto.getUsername(), loginUserDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        return new ResponseEntity(jwtDto, HttpStatus.OK);
    }
}
