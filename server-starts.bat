start cmd /k java -jar my-eureka-server/target/my-eureka-server-0.0.1-SNAPSHOT.jar
timeout 20
start cmd /k java -jar customers/target/customers-0.0.1-SNAPSHOT.jar
start cmd /k java -jar products/target/products-0.0.1-SNAPSHOT.jar
start cmd /k java -jar orders/target/orders-0.0.1-SNAPSHOT.jar
start cmd /k java -jar my-aggregator/target/my-aggregator-0.0.1-SNAPSHOT.jar
start cmd /k java -jar my-gateway/target/my-gateway-0.0.1-SNAPSHOT.jar