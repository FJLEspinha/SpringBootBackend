package com.example.demo.controllers;



// import java.io.File;
//import java.io.FileWriter;
import java.io.IOException;
//import java.io.PrintWriter;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Map;

import com.example.demo.models.Person;
import com.example.demo.services.RickAndMortyService;
import com.example.demo.utils.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;

//import java.util.Random;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
// le indica a Spring boot que es el controlador de tu aplicacion.
@RestController
public class Ejercicio {
    // para que nos de el servicio
    @Autowired
    RickAndMortyService rickAndMortyService;

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

    // http://localhost:8080/sumar?num1=5&num2=2
    @GetMapping("/sumar")
    public String add(@RequestParam String num1,@RequestParam String num2){
        int resultado= Integer.parseInt(num1) + Integer.parseInt(num2);
        //return "La suma de "+num1+" y el numero "+num2+" es "+resultado;
        Object params[]={num1,num2,resultado};
        return MessageFormat.format("La suma de {0} y {1} es {2}", params);
    }
    @PostMapping("/saveProductOnDisk")
    public String saveProductOnDisk(@RequestParam Map<String,String> body){
        
        String articleValue =body.get("article");
        String priceValue = body.get("price");

        if(articleValue.equals("") || priceValue.equals("")){
            return "Error datos incorrectos";
        }
        if(Integer.parseInt(priceValue)<0){
                return "Error el precio es negativo";
            }

            try {
                Utils.save("datos.txt",articleValue+","+priceValue+"\n");
            } catch (IOException e) {
                e.printStackTrace();
                return "Error al guardar en disco";
            }
/*
        FileWriter fw = null;
        PrintWriter pw = null;
        try {
            fw = new FileWriter("datos.txt");
            pw = new PrintWriter(fw);
            pw.print(articleValue+","+priceValue+"\n");
            
            
        } catch (IOException e) {
           e.printStackTrace();
           return "Error la escribir en disco";
        }finally{
            pw.close();
        }*/
        return "produc guardado correct";
    }

    @DeleteMapping("/removeFile")
    public String removeFile(){
        boolean result =Utils.remove("datos.txt");
        return result ? "borrado correcto" : "no se puede borrar";
    }

    // http://localhost:8080/rickandmorty/random
    @GetMapping("/rickandmorty/random")
    public String getRickAndMortyRandomCharacter(){
        // lanzamos una peticion a una API externa
        // salimoa fuera a recoger la foto.
        // lo vamos ha hacer con un servicio
        // inyectamos
        Person c = rickAndMortyService.getCharacterFromAPI();
        return "<img src='"+c.image+"'/>";
      //return  MessageFormat.format("<img src='{0}'/>",c.image);

    }
    //http://localhost:8080/list
    // Trabajamos con un MOTOR DE VISTAS
    @GetMapping("/rickandmorty/list")
    public String getRickAndMortyList(){
       String web="<h1>Lista de personas </h1>";
        ArrayList<Person> persons= rickAndMortyService.getCharactersFromAPI();
       for(Person person : persons){
        web+= "<img src='"+person.image+"'/>";
       }
       return web;
    }
 //http://localhost:8080/chiste?texti= van 2 y se cae el del medio
 @GetMapping("/chiste")
 public String addJoke(@RequestParam String text){
    //INSERT INTO joke (texxt) VALUES("XXXXXXX")
    return "";
 }
    
   
}
