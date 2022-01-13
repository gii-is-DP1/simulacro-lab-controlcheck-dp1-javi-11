package org.springframework.samples.petclinic.product;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.samples.petclinic.pet.PetValidator;
import org.springframework.samples.petclinic.pet.exceptions.DuplicatedPetNameException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/create")
    public String initCreationForm(ModelMap model) {
        Product product=new Product();
        List<ProductType> types=productService.findAllProductTypes();
        model.put("product",product);
        model.put("types",types);
        return "products/createOrUpdateProductForm";

    }
    @InitBinder("product")
    public void initProductBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @PostMapping(value="/update")
    public ModelAndView proccessCreationForm(BindingResult result, @Valid Product product) {

        if (result.hasErrors()) {
            ModelAndView mav=new ModelAndView("/products/createOrUpdateProductForm");
            mav.addObject("product",product);
            return mav;
        } else {
            ModelAndView mav=new ModelAndView("/welcome");
            product.setProductType(result.getModel().get("productType"));
            productService.save(product);
            return mav;
        }
    }


}
