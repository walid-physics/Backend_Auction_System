package miu.cs545.auctionsystem.contoller;

import miu.cs545.auctionsystem.model.BiddingProduct;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class BidderController {
    @PostMapping("/{user_id}/{bidding_product_id}")
    public void bidOnProduct(@PathVariable Integer userId, @PathVariable Integer biddingProductId,
                             @RequestParam Double amount){

    }





}
