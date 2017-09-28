# balance
Qiwi Kazakhstan java task

1.	Разработать схему хранения авторизационных данных клиентов и их балансов.
2.	Номер телефона – мобильный, 10 цифр. Пароль должен храниться в защищенном виде. Балансы – с точность до копеек. 
3. Создать сервлет, принимающий xml-запросы методом POST, который может обрабатывать 2 запроса (см. ниже). Обеспечить целостность данных в БД. Так же учитывать, что к сервлету могут обращаться несколько  пользователей одновременно, с одинаковыми запросами и данными в запросах.
 Регистрация нового агента:
Запрос:
```xml
<?xml version="1.0" encoding="utf-8"?>
<request>
 <request-type>new-agt</request-type>
 <login>1234567890</login> 
 <password>password</password> 
</request>
```xml
Ответ:
```xml 
<?xml version="1.0" encoding="utf-8"?>
<response>
 <result-code>0</result-code>
</response>
```xml
Где код ошибки:
 0  – все хорошо
 1  – такой агент уже  зарегистрирован
 2 – неверный формат телефона
 3 – плохой пароль
 5 – другая ошибка повторите позже

Получение баланса:
Запрос:
```xml
<?xml version="1.0" encoding="utf-8"?>
<request>
 <request-type>agt-bal</request-type>
 <login>1234567890</login> 
 <password>password</password> 
</request>
```xml
Ответ:
```xml
<?xml version="1.0" encoding="utf-8"?>
<response>
  <result-code>0</result-code>
  <bal>100.00</bal>
</response>
```xml
Коды ошибок дописать на свое усмотрение.


