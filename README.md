## OrderSystem
- Uygulama Docker ile postgreSql kullanılarak geliştirilmiştir.
- Bilgisayarınızda docker desktop açıkken Intellij-idea terminalde cd ile dosyanın src/main/resources klasörüne gidin ve 'docker-compose -f docker-compose.yml up -d' komutlarını yazın. Bu komutlar postgreSql ile bağlantı kurmanızı sağlayacaktır. Ancak bunun için intellij-idea'da postgreSql database seçerek kendinize bir database oluşturun. Bu isim application proporties'de yazacağınız database ismi olacak. Docker'ı durdurmak için terminalde yine aynı dizinde 'docker stop $(docker ps -a -q)' kodlarını yazabilirsiniz.
- Proje'de yer alan varlıklar şu şekildedir:
  1. Customer
  2. Product
  3. ProductAttribute
  4. Orders
  5. Favorites
- Varlıklar arası ilişkiler şu şekildedir:
  1. Customer - Orders - OneToMany
  2. Orders - Product - ManyToMany
  3. Product - ProductAttribute - OneToMany
  4. Favorites - Customer - ManyToOne
  5. Favorites - Product - ManyToMany
- Proje kapsamında ürünlerin ait olduğu kategoriler Category enum'unda belirtilmiştir. Bu kapsamda CLOTHING kategorisindeki ürünlerin size attribute'una M;S;L gibi beden bilgileri dışında bir bilgi yazılamaması, benzer olarak SHOES kategorisindeki ürünlerin size attribute'una 38;39;40 gibi beden bilgileri dışında bir bilgi yazılamaması AttributeValidator classında yazılan fonksiyonlarla sağlanmıştır.
- Proje kapsamında yapılan endpointler şu şekildedir:
### @GetMapping -> 
  1. localhost:8080/api/customer/listAllCustomers -> Tüm müşterilerin listelenmesi
  2. localhost:8080/api/product/5 -> Ürünlerin id'ye göre listelenmesi
  3. localhost:8080/api/product/getAllProducts -> Tüm ürünlerin listelenmesi
  4. localhost:8080/api/order/getAllOrders -> Tüm siparişlerin listelenmesi
  5. localhost:8080/api/order/listOrdersByProductId/1 -> Ürün id'sine göre siparişlerin listelenmesi
  6. localhost:8080/api/product/getProductsByCategory?category=SHOES -> Kategorilere göre ürünlerin listelenmesi (@RequestParam)
  7. localhost:8080/api/favorites/getAllFavLists -> Tüm favori listelerinin listelenmesi

### @PostMapping ->
 1. localhost:8080/api/customer/createCustomer -> Müşteri oluşturma -> Postman isteği =
 > {
    "name":"CustomerName",
    "surname":"CustomerSurname",
    "email":"Customer@gmail.com",
    "password":"Customer123"
}
  3. localhost:8080/api/product/createProduct -> Ürün oluşturma -> Postman isteği =
 > {
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
  3. localhost:8080/api/order/createOrder -> Sipariş oluşturma -> Postman isteği =
 > {
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
  5. localhost:8080/api/order/deliverOrder/629173 -> Sipariş id'sine göre sipariş teslim etme
  6. localhost:8080/api/order/cancelOrder/111040 -> Sipariş id'sine göre sipariş iptal etme
  7. localhost:8080/api/favorites/createFavList -> Favori Listesi oluşturma -> Postman isteği =
> {
    "listName":"Shoes",
    "customer":{"id" : 1},
    "products":[
        {"id":1},
        {"id":2}
    ]
}
