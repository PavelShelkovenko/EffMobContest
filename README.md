# Тестовое задания (Приложение для поиска работы)

## В этом приложение вы можете:
  - Искать интересные вам вакансии
  - Просматривать детальную информацию о них
  - Добавлять вакансии в избранное

#### В проекте использовались следующие библиотеки:

- MVVM, Clean Architecture
- Многомодульность
- AdapterDelegates
- [Room](https://developer.android.com/jetpack/androidx/releases/room) - Сохранения информации о стримах, профилях, последних сообщениях в чатах
- [Retrofit2](https://github.com/square/retrofit) - Работа с сетью
- [Okhttp3](https://github.com/square/okhttp) - Логирования запросов в сеть
- [Koin](https://insert-koin.io/) - Инъекция зависимостей
- [Glide](https://github.com/bumptech/glide) - Загрузка изображений
- [Kotlin Coroutines](https://github.com/Kotlin/kotlinx.coroutines/) - Асинхронная работы
- [Jetpack's Navigation component](https://developer.android.com/guide/navigation) - Навигация между экранами приложения

Решил отказаться от создания пакетов (data, domain, presentation) в каждом модуле и выделить отдельные data и domain модули на весь проект, так как у нас только 1 запрос на все приложение(1 источник данных).
  
<img src="https://github.com/PavelShelkovenko/EffMobContest/blob/master/feature_main_screen_record.gif" alt="App preview" width="360" height="840"/><img src="https://github.com/PavelShelkovenko/EffMobContest/blob/master/feature_login_screen_record.gif" alt="App preview" width="360" height="840"/>  
<img src="https://github.com/PavelShelkovenko/EffMobContest/blob/master/feature_favorite_screen_record.gif" alt="App preview" width="360" height="840"/><img src="https://github.com/PavelShelkovenko/EffMobContest/blob/master/feature_details_screen_recording.gif" alt="App preview" width="360" height="840"/> 
