# Integrate VNPay into Springboot Application
This guide demonstrates how to integrate VNPay, a popular payment gateway in Vietnam, into a Spring Boot application. VNPay provides a secure and reliable platform for processing online payments.

# Prerequisites

Before getting started, ensure you have the following:

* Java Development Kit (JDK) installed on your machine
* Maven or Gradle installed for dependency management
* Access to VNPay API credentials (Merchant ID, Access Key, Secret Key)
* Basic understanding of Spring Boot framework

# **Setup**
1. Clone the repository:
  ```bash
   git clone https://github.com/tantd2203/VN-PAY.git
  ```
2. Navigate to the project directory:
 ```bash
    cd VN-PAY
  ```

# **2. Configure VNPay Properties**

   In your **application.properties** configure VNPay properties including Merchant ID, Access Key, Secret Key, and other required configurations.

Example application.properties:
```
vnpay.merchantId=your_merchant_id
vnpay.accessKey=your_access_key
vnpay.secretKey=your_secret_key
vnpay.returnUrl=http://localhost:8080/payment-return ??
```

ss