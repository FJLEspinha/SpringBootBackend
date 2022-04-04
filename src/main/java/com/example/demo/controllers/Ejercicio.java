package com.example.demo.controllers;



import com.example.demo.utils.Utils;

//import java.util.Random;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
// le indica a Spring boot que es el controlador de tu aplicacion.
@RestController
public class Ejercicio {
    // cuando alguien entre en http://localhost:8080/ hace esto.
    @GetMapping("/")
    public String greet(){
        return "Bienvenido al servidor backend";
    }
    // cuando alguien entre en http://localhost:8080/aleatorio hace esto.
    @GetMapping("/aleatorio")
    public String randomNumber(){
        long r= Math.round(Math.random()*101);
        return r + "";
    }
    // cuando alguien entre en http://localhost:8080/palindromo/ana ana de ej hace esto
    @GetMapping("/palindromo/{name}")
    public String palindrome(@PathVariable String name){
       // StringBuilder builder = new StringBuilder(name);
        //String reversedName = builder.reverse().toString();
        boolean palindrome = Utils.isPalindrome(name);
       // if(name.equals(reversedName)) return "Si es un palindromo";
        //else  return "No es un palindromo";
        return palindrome ? "Si es palindromo" : "No es palindromo";
    }

    
}
