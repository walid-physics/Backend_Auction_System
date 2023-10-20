package miu.cs545.auctionsystem.contoller;

import miu.cs545.auctionsystem.model.BiddingProduct;
import miu.cs545.auctionsystem.model.Product;
import miu.cs545.auctionsystem.service.BiddingProductService;
import miu.cs545.auctionsystem.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bidding_products")
public class BiddingProductController {
    private BiddingProductService biddingProductService;

    @Autowired
    public BiddingProductController(BiddingProductService biddingProductService){
        this.biddingProductService=biddingProductService;
    }

    @GetMapping()
    public List<BiddingProduct> getALlProducts(){
        return biddingProductService.getBiddingProducts();
    }

    @GetMapping("/{id}")

    public BiddingProduct getProductById(@PathVariable Integer id){
        return biddingProductService.getBiddingProductById(id);
    }

    @PostMapping
    public BiddingProduct saveProduct(@RequestBody BiddingProduct biddingProduct){
       return biddingProductService.addBiddingProduct(biddingProduct);
    }

    @PutMapping
    public BiddingProduct updateProduct(@RequestBody BiddingProduct biddingProduct){
        return biddingProductService.updateBiddingProduct(biddingProduct);
    }
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Integer id){
     biddingProductService.deleteBiddingProductById(id);
    }


}
