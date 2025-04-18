```text
Задание 3.
Расскажите, как в выбранном вами языке программирования поддерживается концепция "класс как модуль".
Пока мы говорим именно про "классы" в смысле модули -- пространство имен, сборки, пакеты, библиотеки 
-- все что содержит готовый код и что можно импортировать в свою программу.
```

В языке Java каждый класс в Java является автономной единицей по сути модулем,
в отдельном файле с совпадающим именем. Содержит набор полей и методов, конструкторов 
и является основной экспортируемой единицей файла, икнкапсулирует логику 
и предоставляет интерфейс для взаимодействия.

Для последующей группировки, классы группируются в пакеты(packages), организуя пространство имен.

На уровне пакета классы используют модификаторы доступа (public, private, protected и package-private),
которые контролируют видимость классов и их членов: они позволяют ограничить доступ
к внутренней реализации внутри пакета и предоставить только публичный интерфейс для пользователей модуля.

Использование классов и пакетов осуществляется с помощью механизма импорта,
что позволяет подключать готовые модули в другие проекты и переиспользовать их функциональность.