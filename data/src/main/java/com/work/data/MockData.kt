package com.work.data

import com.google.gson.Gson
import com.google.gson.JsonObject

object MockData {
    private const val jsonString = """
    {
      "courses": [
        {
          "id": 100,
          "title": "Java-разработчик с нуля",
          "text": "Освойте backend-разработку и программирование на Java, фреймворки Spring и Maven, работу с базами данных и API. Создайте свой собственный проект, собрав портфолио и став востребованным специалистом для любой IT компании.",
          "price": "999",
          "rate": "4.9",
          "startDate": "2024-05-22",
          "hasLike": false,
          "publishDate": "2024-02-02"
        },
        {
          "id": 101,
          "title": "Kotlin для мобильной разработки",
          "text": "Научитесь создавать мобильные приложения на Kotlin с использованием Jetpack Compose и Android SDK. Практические проекты помогут освоить современные подходы к разработке.",
          "price": "799",
          "rate": "4.7",
          "startDate": "2024-06-01",
          "hasLike": true,
          "publishDate": "2024-03-15"
        },
        {
          "id": 102,
          "title": "Android Developer Pro",
          "text": "Погрузитесь в мир Android-разработки: от основ до продвинутых тем, включая архитектуру, CI/CD и публикацию приложений.",
          "price": "1299",
          "rate": "4.8",
          "startDate": "2024-07-10",
          "hasLike": false,
          "publishDate": "2024-04-20"
        }
      ]
    }
    """

    fun getCourses(): List<Course> {
        val gson = Gson()
        val jsonObject = gson.fromJson(jsonString, JsonObject::class.java)
        val coursesArray = jsonObject.getAsJsonArray("courses")
        return gson.fromJson(coursesArray, Array<Course>::class.java).toList()
    }
}