package com.example.mevenproject.controller;


import com.example.mevenproject.dto.PayPalCustomerCreate;
import com.example.mevenproject.dto.PayPalPayment;
import com.example.mevenproject.service.PayPalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class PaypalPayment {
    @Autowired
    private PayPalService payPalService;


    @PostMapping("/addCustomer")
    public ResponseEntity<?> createCustomer(@RequestBody PayPalCustomerCreate customer) {

        ResponseEntity<?> entity;
        try {
           String customer1 = payPalService.createCustomer(customer);

            System.out.println(customer1);

            entity = new ResponseEntity<>("Customer created successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            entity = new ResponseEntity<>("Customer already present", HttpStatus.CONFLICT);
        }
        return entity;
    }

    @PostMapping("/transaction/{payMethodNonce}")
    public ResponseEntity<?> createTransaction(@RequestBody PayPalPayment payment, @PathVariable String payMethodNonce)
    {
        ResponseEntity<?> entity;
        try {
            payPalService.createTransaction(payMethodNonce,payment);
            entity = new ResponseEntity<>("Transaction is successful", HttpStatus.OK);
        } catch (Exception e) {
            entity = new ResponseEntity<>("Transaction is failed", HttpStatus.BAD_REQUEST);
        }
        return entity;
    }
}


//create Payout
       /* @PostMapping("/payout")
        public ResponseEntity<?> createPayout(@RequestBody PayoutDto payoutDto) {
        ResponseEntity<?> entity;
        try {
            PayoutUser user = paypalPayoutService.createHyperWallerUser(payoutDto);
            entity = new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (Exception e) {
            entity = new ResponseEntity<>("User not created", HttpStatus.BAD_REQUEST);
        }
        return entity;
    }

        @PostMapping("/payment")
        public ResponseEntity<?> createPayment(@RequestBody Payment payment) {
        ResponseEntity<?> entity;
        try {
            HyperwalletPayment hyperwalletPayment = paypalPayoutService.createPayment(payment);
            entity = new ResponseEntity<>(hyperwalletPayment, HttpStatus.OK);
        } catch (Exception e) {
            entity = new ResponseEntity<>("Transaction is failed", HttpStatus.BAD_REQUEST);
        }
        return entity;
    }
        @GetMapping("/user/{user}")
        public ResponseEntity<?> getHyperwalletUser(@PathVariable("user") String user){
        ResponseEntity<?> entity;
        PayoutUser payoutUser = paypalPayoutService.getHyperWalletUser(user);
        try {
            entity = new ResponseEntity<>(payoutUser,HttpStatus.OK);
        }
        catch (Exception e){
            entity = new ResponseEntity<>("User not exists", HttpStatus.BAD_REQUEST);
        }
        return entity;
    }

        @GetMapping("/hello")
        public ResponseEntity<?> hello() {
        return new ResponseEntity<>("Hello",HttpStatus.OK);
    }

        @GetMapping("/userToken/{userToken}")
        public ResponseEntity<?> getAuthToken(@PathVariable("userToken") String userToken){
        ResponseEntity<?> entity;
        HyperwalletAuthenticationToken authenticationToken = paypalPayoutService.getAuthenticationToken(userToken);
        try {
            entity = new ResponseEntity<>(authenticationToken,HttpStatus.OK);
        }
        catch (Exception e){
            entity = new ResponseEntity<>("UserToken not exists", HttpStatus.BAD_REQUEST);
        }
        return entity;
    }
    }

        */
