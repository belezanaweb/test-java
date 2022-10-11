package br.com.blz.testjava

import br.com.blz.testjava.model.Product
import com.google.gson.Gson
import org.springframework.stereotype.Component
import java.io.File
import java.io.IOException


object ProductTestData {

  @Throws(IOException::class)
  fun produtoCompletoTesteGithubMockado(): Product? {
    val jsonString: String = produtoCompletoTesteGithubString()
    return Gson().fromJson(jsonString, Product::class.java)
  }

  @Throws(IOException::class)
  fun productNotMarketable(): Product? {
    val jsonString: String = fileToString("src/test/java/br/com/blz/testjava/templates/product_not_marketable.json")
    return Gson().fromJson(jsonString, Product::class.java)
  }

  @Throws(IOException::class)
  fun productMarketable(): Product? {
    return produtoCompletoTesteGithubMockado()
  }

  @Throws(IOException::class)
  fun produtoCompletoTesteGithubString(): String {
    return fileToString("src/test/java/br/com/blz/testjava/templates/product_mock_string_completo.json")
  }

  private fun fileToString(filePath: String): String {
    return File(filePath).readText(Charsets.UTF_8)
  }
}
