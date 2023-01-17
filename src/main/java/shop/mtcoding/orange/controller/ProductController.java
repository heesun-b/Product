package shop.mtcoding.orange.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import shop.mtcoding.orange.model.Product;
import shop.mtcoding.orange.model.ProductRepository;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository; // 타입으로 찾아냄 - xml을 구현한 productRepository의 자식 클래스를 찾아냄

    @Autowired
    private HttpSession session;

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @GetMapping("/redirect")
    public void redirect(HttpServletRequest request, HttpServletResponse response) throws IOException {
        session.setAttribute("name", "session metacoding");
        request.setAttribute("name", "metacoding"); // name이 같으면 크기가 작은 것부터 찾음 page>request>session
        response.sendRedirect("/test");
        return;
    }

    @GetMapping("/dispatcher")
    public void dispatcher(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("name", "metacoding"); // = model.addAttribute()
        RequestDispatcher dis = request.getRequestDispatcher("/test");
        dis.forward(request, response);
    }

    @GetMapping({ "/", "/product" })
    public String findAll(Model model) {

        List<Product> productList = productRepository.findAll();
        model.addAttribute("productList", productList);
        return "product/main";
    }

    @GetMapping("/api/product")
    @ResponseBody
    public List<Product> apiFindAllProduct() {
        List<Product> productList = productRepository.findAll();
        return productList;
    }

    @GetMapping("/api/product/{id}")
    @ResponseBody
    public Product apiFindOneProduct(@PathVariable int id) {
        Product product = productRepository.findOne(id);
        return product;
    }

    @GetMapping("/product/{id}")
    public String findOne(@PathVariable int id, Model model) {

        Product product = productRepository.findOne(id);
        model.addAttribute("productId", product);
        return "product/detail";
    }

    @GetMapping("/product/addForm")
    public String addForm() {
        return "product/addForm";
    }

    @PostMapping("/product/add")
    public String add(String name, int price, int qty) {
        int result = productRepository.insert(name, price, qty);
        if (result == 1) {
            return "redirect:/product";
        } else {
            return "redirect:/product/addForm";
        }
    }

    @PostMapping("/product/{id}/delete")
    public String delete(@PathVariable int id) {
        int result = productRepository.delete(id);
        if (result == 1) {
            return "redirect:/product";
        } else {
            return "redirect:/product/" + id;
        }
    }

    @GetMapping("/product/{id}/updateForm")
    public String update(@PathVariable int id, Model model) {
        Product product = productRepository.findOne(id);
        model.addAttribute("productId", product);
        return "product/updateForm";
    }

    @PostMapping("/product/{id}/update")
    public String update(@PathVariable int id, String name, int price, int qty) {
        int result = productRepository.update(id, name, price, qty);
        if (result == 1) {
            return "redirect:/product/" + id;
        } else {
            return "redirect:/product/updateForm";
        }
    }
}
