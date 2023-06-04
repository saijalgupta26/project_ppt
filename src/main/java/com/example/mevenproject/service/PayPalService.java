package com.example.mevenproject.service;

import com.braintreegateway.*;
import com.example.mevenproject.dto.PayPalCustomerCreate;
import com.example.mevenproject.dto.PayPalPayment;
import org.springframework.stereotype.Service;


@Service
public class PayPalService {

    private static BraintreeGateway gateway()
    {
        BraintreeGateway gateway = new BraintreeGateway(Environment.SANDBOX,"7kdbwf2wqk9wnr88","b5f6czwt8z48gb3x","8612f3d4882e6adc602e2e2b9e8a3a69");

        return gateway;
    }

    public String createCustomer(PayPalCustomerCreate payPalCustomer)
    {

        CustomerRequest request = new CustomerRequest()
                .firstName(payPalCustomer.getFirstName())
                .lastName(payPalCustomer.getLastName())
                .email(payPalCustomer.getEmail())
                .phone(payPalCustomer.getContactNo())
                .paymentMethodNonce(payPalCustomer.getPayMethodNonce());


        Result<Customer> result = gateway().customer().create(request);

        result.isSuccess();

        Customer customer = result.getTarget();
        String id = customer.getId();
        System.out.println(id);

        PaymentMethod paymentMethod = customer.getPaymentMethods().get(0);
        /*customer.getPaymentMethods().get(0).getToken();*/

        return  customer.getId();
    }
    public void createTransaction(String paymentMethodNonce, PayPalPayment payment){
        AddressRequest addressRequest=new AddressRequest()
                .countryName("usa")
                .postalCode("122121");
        System.out.println(addressRequest);

        System.out.println();
        TransactionRequest request = new TransactionRequest()
                .customerId("43181964723")
                .amount(payment.getAmount())
//                .billingAddressId()

                .paymentMethodNonce(paymentMethodNonce)


                .paypalAccount()
                .done();

        Result<Transaction> result = gateway().transaction().sale(request);
        if (result.isSuccess()) {
            System.out.println();
        } else {
            System.out.println();
        }
    }
}



