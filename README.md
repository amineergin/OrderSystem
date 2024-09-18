# OrderSystem
- Uygulama customer, product, comment, order entitylerinden oluşur.
- Docker ile postgreSql kullanılarak geliştirilmiştir.
- Bilgisayarınızda docker desktop açıkken Intellij-idea terminalde cd ile dosyanın src/main/resources klasörüne gidin ve 'docker-compose -f docker-compose.yml up -d' komutlarını yazın. Bu komutlar postgreSql ile bağlantı kurmanızı sağlayacaktır. Ancak bunun için intellij-idea'da postgreSql database seçerek kendinize bir database oluşturun. Bu isim application proporties'de yazacağınız database ismi olacak. Docker'ı durdurmak için terminalde yine aynı dizinde 'docker stop $(docker ps -a -q)' kodlarını yazabilirsiniz.
- Proje'de yer alan varlıklar şu şekildedir:
  1. Customer
  2. Product
  3. ProductAttribute
  4. Orders
- Varlıklar arası ilişkiler şu şekildedir:
  1. Customer - Orders - OneToMany
  2. Orders - Product - ManyToMany
  3. Product - ProductAttribute - OneToMany
- Proje kapsamında ürünlerin ait olduğu kategoriler Category enum'unda belirtilmiştir. Bu kapsamda CLOTHING kategorisindeki ürünlerin size attribute'una M;S;L gibi beden bilgileri dışında bir bilgi yazılamaması, benzer olarak SHOES kategorisindeki ürünlerin size attribute'una 38;39;40 gibi beden bilgileri dışında bir bilgi yazılamaması AttributeValidator classında yazılan fonksiyonlarla sağlanmıştır.
- Proje kapsamında yapılan endpointler şu şekildedir:
1. @GetMapping ->
       1.1) localhost:8080/api/customer/listAllCustomers -> Tüm müşterilerin listelenmesi
       1.2) localhost:8080/api/product/5 -> Ürünlerin id'ye göre listelenmesi
       1.3) localhost:8080/api/product/getAllProducts -> Tüm ürünlerin listelenmesi
       1.4) localhost:8080/api/order/getAllOrders -> Tüm siparişlerin listelenmesi
       1.5) localhost:8080/api/order/listOrdersByProductId/1 -> Ürün id'sine göre siparişlerin listelenmesi
       1.6) localhost:8080/api/product/getProductsByCategory?category=SHOES -> Kategorilere göre ürünlerin listelenmesi (@RequestParam)

2. @PostMapping ->
       2.1) localhost:8080/api/customer/createCustomer -> Müşteri oluşturma -> Postman isteği = 
- {
    "name":"CustomerName",
    "surname":"CustomerSurname",
    "email":"Customer@gmail.com",
    "password":"Customer123"
}
        2.2) localhost:8080/api/product/createProduct -> Ürün oluşturma -> Postman isteği =
  - {
    "title":"Shoe",
    "description":"Casual",
    "price":1350,
    "brand":"Nike",
    "stock":150,
    "category":"SHOES",
    "attributes": [
        {
            "attributeName": "size",
            "attributeValue" : 38
        },
        {
            "attributeName":"color",
            "attributeValue":"red"
        }
    ]
}

        2.3) localhost:8080/api/order/createOrder -> Sipariş oluşturma -> Postman isteği =
    - {
    "customer" : {
        "id" : 1
    },
    "cargoName":"Aras Kargo",
    "products":[
        {
            "id" : 1
        }
    ]
}

      2.4) localhost:8080/api/order/deliverOrder/629173 -> Sipariş id'sine göre sipariş teslim etme
      2.5) localhost:8080/api/order/cancelOrder/111040 -> Sipariş id'sine göre sipariş iptal etme
