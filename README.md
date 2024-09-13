# OrderSystem
- Uygulama customer, product, comment, order entitylerinden oluşur.
- Docker ile postgreSql kullanılarak geliştirilmiştir.
- Bilgisayarınızda docker desktop açıkken Intellij-idea terminalde cd ile dosyanın src/main/resources klasörüne gidin ve 'docker-compose -f docker-compose.yml up -d' komutlarını yazın. Bu komutlar postgreSql ile bağlantı kurmanızı sağlayacaktır. Ancak bunun için intellij-idea'da postgreSql database seçerek kendinize bir database oluşturun. Bu isim application proporties'de yazacağınız database ismi olacak.
- Proje'de kullanıcının sipariş oluşturması, ürünlere yorum yapması, yorum silinebilmesi, kullanıcının tüm bilgilerini ya da bazı bilgilerini güncellemesi sağlanmıştır.
- Gelecekte projede siparişlere yorum yapma özelliği ve satıcı rolü eklenecektir.
- Varlıklar arası ilişkiler şu şekildedir:
    Customer-Order -> OneToMany
    Customer-Comment -> OneToMany
    Product-Comment -> OneToMany
    Product-Order -> ManyToMany
  
