# Приложение для просмотра курсов.

---

## Технологии

- **Kotlin**
- **Jetpack Compose** (UI)
- **MVVM + Clean Architecture**
- **Koin** (DI)
- **Coroutines + Flow**
- **KStore** (локальное хранилище)
- **Многомодульность**

---

## Модули

:app      → UI, навигация

:uikit    → кастомные Compose-компоненты

:domain   → use cases, модели

:data     → репозитории, KStore

---

## Отклонения от ТЗ

| Требование       | Реализация             | Причина |
|------------------|------------------------|--------|
| **XML**          | → **Compose**          | Разрешено техподдержкой |
| **Retrofit**     | → **Мок-данные**       | Нет API |
| **Room**         | → **KStore**           | Достаточно для ID избранного |
| **AdapterDelegates** | → `LazyColumn`     | Не нужен в Compose |

---

## Запуск

```bash
./gradlew :app:assembleDebug
