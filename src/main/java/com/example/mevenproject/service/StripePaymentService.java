package com.example.mevenproject.service;




import com.example.mevenproject.dto.*;
import com.example.mevenproject.exception.PaymentMethodNotFound;
import com.example.mevenproject.exception.PaymentNotConnect;
import com.example.mevenproject.repository.PaymentIdRepository;
import com.example.mevenproject.repository.PaymentMethodRepository;
import com.example.mevenproject.repository.StripeRepository;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.PaymentIntent;
import com.stripe.model.PaymentMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class StripePaymentService {

    @Autowired
    private StripeRepository stripeRepository;
    @Autowired
    private PaymentMethodRepository paymentMethodRepository;
    @Autowired
    private PaymentIdRepository paymentIdRepository;

    @Value("${stripe.apikey}")
    String stripekey;
    public String customerCreate(CustomerDetails customerDetails) throws StripeException, StripeException {
        Stripe.apiKey = stripekey;
        Optional<CustomerDetails> customer = stripeRepository.findByemail(customerDetails.getEmail());
        if(customer.isPresent())
        {
            return customer.get().getId();
        }
        else {
            Map<String, Object> params = new HashMap<>();
            params.put("name", customerDetails.getName());
            params.put("email", customerDetails.getEmail());
            Customer customer1 = Customer.create(params);
            customerDetails.setId(customer1.getId());
            stripeRepository.save(customerDetails);
            return customer1.getId();

        }

    }

    public String paymentCreate(Card card1) throws StripeException {
        Stripe.apiKey = stripekey;
        Optional<Card> paymentMethod = paymentMethodRepository.findByNumberAndCvc(card1.getNumber(), card1.getCvc());
        if(paymentMethod.isPresent())
        {
            return paymentMethod.get().getId();
        }
        else
        {
            Map<String, Object> card = new HashMap<>();
            card.put("number", card1.getNumber());
            card.put("exp_month", card1.getExp_month());
            card.put("exp_year", card1.getExp_year());
            card.put("cvc", card1.getCvc());
            Map<String, Object> params = new HashMap<>();
            params.put("type", "card");
            params.put("card", card);

            PaymentMethod paymentMethod1 =
                    PaymentMethod.create(params);
            card1.setId(paymentMethod1.getId());
            paymentMethodRepository.save(card1);
            return card1.getId();
        }
    }

    public String paymentWithCustomer(String customerId, String paymentMethodId, Payment payment) throws StripeException {
        payment = new Payment();
        Stripe.apiKey=stripekey;
        Optional<Payment> byCustomerIdAndPaymentId = paymentIdRepository.findByCustomerIdAndPaymentId(customerId, paymentMethodId);

        if(byCustomerIdAndPaymentId.isPresent())
        {
            return byCustomerIdAndPaymentId.get().getPaymentId();

        }
        else {
            PaymentMethod paymentMethod =
                    PaymentMethod.retrieve(
                            paymentMethodId
                    );


            Map<String, Object> params = new HashMap<>();
            params.put("customer", customerId);
            PaymentMethod updatedPaymentMethod = paymentMethod.attach(params);
            payment.setPaymentId(updatedPaymentMethod.getId());
            payment.setCustomerId(customerId);
            payment.setPaymentMethodId(paymentMethodId);
            System.out.println(payment);
            paymentIdRepository.save(payment);
            return updatedPaymentMethod.getId();

        }

    }

    public String paymentIntent(String customerId , String paymentMethodId, PaymentRequest cardResponse, AddressRequest address, String name) throws StripeException {
        Stripe.apiKey =stripekey;
        Map<String, Object> params = new HashMap<>();

        params.put("amount", cardResponse.getAmount());
        params.put("currency", "usd");
        params.put("customer",customerId);
        params.put("payment_method", paymentMethodId);
        params.put("off_session", true);
        params.put("confirm", true);
        params.put("description","transaction");
        params.put("payment_method_types", Arrays.asList("card"));
        params.put("shipping", new HashMap<String, Object>() {{
            put("name", "Customer Name");
            put("address", new HashMap<String, Object>() {{
                put("line1", address.getLine1());
                put("city", address.getCity());
                put("state", address.getState());
                put("postal_code", address.getZipcode());
                put("country", address.getCountry());
            }});
        }});
        PaymentIntent intent = PaymentIntent.create(params);
        return intent.getId();
    }
    public String customerAlreadyExists(CustomerDetails customerDetails) throws ClassNotFoundException {
        Optional<CustomerDetails> customer = stripeRepository.findByemail(customerDetails.getEmail());
        if(customerDetails!=null)
        {
            CustomerDetails customerDetails1 = customer.get();
            return customerDetails1.getId();
        }
        else{
            throw  new ClassNotFoundException("customer not exists");
        }
    }
    public String paymentMethodAlreadyCreated(Card card1) throws PaymentMethodNotFound {
        Optional<Card> byCardNumber = paymentMethodRepository.findByNumberAndCvc(card1.getNumber(),card1.getCvc());
        if(byCardNumber.isPresent())
        {
            Card card = byCardNumber.get();
            return card.getId();
        }
        else{
            throw new PaymentMethodNotFound("payment Method is not found");
        }
    }
    public String paymentWithCustomer(String customerId,String paymentMethodId) throws PaymentNotConnect {
        Optional<Payment> payment = paymentIdRepository.findByCustomerIdAndPaymentId(customerId, paymentMethodId);
        if(payment!=null)
        {
            return payment.get().getPaymentId();
        }
        else{
            throw new PaymentNotConnect("payment not connected with customer");
        }
    }

}
