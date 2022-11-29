package ro.nicolaemariusghergu.queryit.proxy

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import ro.nicolaemariusghergu.queryit.dto.CategoryDto
import ro.nicolaemariusghergu.queryit.dto.ProductDto
import ro.nicolaemariusghergu.queryit.dto.PromotionDto
import ro.nicolaemariusghergu.queryit.model.Manufacturer

@FeignClient(name = "products", url = "http://localhost:9092")
interface ProductProxy {
    @GetMapping("/api/download/v1/mega-image/products/{categoryId}")
    open fun getProductsFromMegaImageByCategory(@PathVariable categoryId: Long?): ResponseEntity<MutableSet<ProductDto?>?>?
    @GetMapping("/api/download/v1/mega-image/products")
    open fun getProductsFromMegaImage(): ResponseEntity<MutableSet<ProductDto?>?>?
    @GetMapping("/api/download/v1/mega-image/promotions")
    open fun getPromotionsFromMegaImage(): ResponseEntity<MutableSet<PromotionDto?>?>?
    @GetMapping("/api/download/v1/mega-image/product-promotions")
    open fun getProductsWithPromotionFromMegaImage(): ResponseEntity<MutableSet<ProductDto?>?>?
    @GetMapping("/api/download/v1/mega-image/categories")
    open fun getCategoriesFromMegaImage(): ResponseEntity<MutableSet<CategoryDto?>?>?
    @GetMapping("/api/download/v1/mega-image/manufacturers")
    open fun getManufacturersFromMegaImage(): ResponseEntity<MutableSet<Manufacturer?>?>?
}