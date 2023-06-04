package com.example.mevenproject.controller;
import com.example.mevenproject.dto.Data;
import com.example.mevenproject.exception.CustomerNotFoundException;
import com.example.mevenproject.exception.PaymentMethodNotFound;
import com.example.mevenproject.service.StripePaymentService;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
public class StripePayment {
    @Autowired
    private StripePaymentService stripePaymentService;
    @Autowired
    Data data;

    @PostMapping("stripePayment")
    public ResponseEntity<?> stripePayment(@RequestBody Data data) throws StripeException, StripeException {

        String customerId = stripePaymentService.customerCreate(data.getCustomerDetails());
        String paymentMethod = stripePaymentService.paymentCreate(data.getCard());
        String paymentId = stripePaymentService.paymentWithCustomer(customerId, paymentMethod,data.getPayment());
        stripePaymentService.paymentIntent(customerId,paymentMethod,data.getCardResponse(),data.getAddress(),data.getCustomerDetails().getName());
        return new ResponseEntity<>("successfully", HttpStatus.FOUND);
        //name,email
    }
    @PostMapping("customerAlreadyExists")
    public ResponseEntity<?> stripePayment1(@RequestBody Data data) throws StripeException, CustomerNotFoundException, ClassNotFoundException {
        String customerId = stripePaymentService.customerAlreadyExists(data.getCustomerDetails());

        String paymentMethod = stripePaymentService.paymentCreate(data.getCard());
        String paymentId = stripePaymentService.paymentWithCustomer(customerId, paymentMethod,data.getPayment());
        stripePaymentService.paymentIntent(customerId,paymentMethod,data.getCardResponse(),data.getAddress(),data.getCustomerDetails().getName());

        return new ResponseEntity<>("successfully", HttpStatus.FOUND);

    }
    @PostMapping("paymentMethodAlreadyExist")
    public ResponseEntity<?> stripePayment2(@RequestBody Data data) throws CustomerNotFoundException, PaymentMethodNotFound, StripeException, ClassNotFoundException, PaymentMethodNotFound {
        String customerId = stripePaymentService.customerAlreadyExists(data.getCustomerDetails());
        String paymentMethod = stripePaymentService.paymentMethodAlreadyCreated(data.getCard());

        stripePaymentService.paymentIntent(customerId,paymentMethod,data.getCardResponse(),data.getAddress(),data.getCustomerDetails().getName());

        return new ResponseEntity<>("successfully", HttpStatus.FOUND);
        

    }
    @GetMapping("hello")
    public ModelAndView hello()
    {
        return new ModelAndView("stripeForm");
    }



}
