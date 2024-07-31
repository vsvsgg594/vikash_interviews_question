package com.store.crud.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.store.crud.model.ProductDto;
import com.store.crud.model.Products;
import com.store.crud.repo.ProductRepo;

import jakarta.validation.Valid;



@Controller
public class AppController {

    @Autowired
    private ProductRepo repo;
   

    @GetMapping("/")
    public String home(Model model){
        List<Products> products=repo.findAll();
        model.addAttribute("products",products);

    
        return "index";
    }
    @GetMapping("/create")
    public String showCreatePage(Model model) {
       ProductDto productDto=new ProductDto();
       model.addAttribute("productDto", productDto);
       return "create";
    }
    @PostMapping("/createdProduct")
    public String postProduct(@Valid @ModelAttribute ProductDto productDto,BindingResult result ){

        Products products=new Products();
        products.setName(productDto.getName());
        products.setBrand(productDto.getBrand());
        products.setCategory(productDto.getCategory());
        products.setDescription(productDto.getDescription());
        products.setImageFileName(productDto.getImage());
        products.setPrice(productDto.getPrice());
        repo.save(products);
        return "redirect:/";

    }
    @GetMapping("/edit")
    public String showEditPage(Model model,@RequestParam int id){
        try{
            Products product=repo.findById(id).get();
            model.addAttribute("product", product);

        }catch(Exception e){
            System.out.println(e.getMessage());
            return "redirect:/";

        }
        return "/editpage";




    }
    @GetMapping("/delete")
    public String deleteProduct(@RequestParam int id){
        try{
            Products product=repo.findById(id).get();
            repo.delete(product);;

        }catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return "redirect:/";

    }
}
