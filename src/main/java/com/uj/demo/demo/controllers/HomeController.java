package com.uj.demo.demo.controllers;
import com.uj.demo.demo.services.HomeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import com.uj.demo.demo.services.ProductService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.apache.logging.log4j.*;

@Controller
public class HomeController {
    private final ProductService productService;
    private final HomeService homeService;

    private static final Logger logger = LogManager.getLogger(HomeController.class);

    public HomeController(ProductService productService, HomeService homeService) {
        this.productService = productService;
        this.homeService = homeService;
    }

    @GetMapping("/")
    public String index(Model model, HttpSession session) {
        logger.info("Loading index page");
        return homeService.index(model, session, productService);
    }

}
