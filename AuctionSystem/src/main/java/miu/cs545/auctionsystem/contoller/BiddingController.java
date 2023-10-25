package miu.cs545.auctionsystem.contoller;


import lombok.RequiredArgsConstructor;
import miu.cs545.auctionsystem.model.Product;
import miu.cs545.auctionsystem.service.ProductService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bidding")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BiddingController
{
    private final ProductService productService;

    @PostMapping("/addbid/{id}/{amount}")
    public Product addBid(@PathVariable Integer id, @PathVariable  Double amount) throws Exception {
       return productService.addBid(id,amount);
    }
}
