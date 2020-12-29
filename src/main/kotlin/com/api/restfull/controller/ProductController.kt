package com.api.restfull.controller

import com.api.restfull.model.*
import com.api.restfull.service.ProductService
import org.springframework.web.bind.annotation.*

/**
 * @author Rizki Rian Anandita
 */
@RestController
class ProductController(val productService: ProductService) {

    @PostMapping(
            value = ["/api/products"],
            produces = ["application/json"],
            consumes = ["application/json"]
    )
    fun createProduct(@RequestBody body: CreateProductRequest): WebResponse<ProductResponse> {
        val productResponse = productService.create(body)
        return WebResponse(
                code = 200,
                status = "OK",
                data = productResponse
        )
    }

    @GetMapping(
            value = ["/api/products/{id_product}"],
            produces = ["application/json"]
    )
    fun getProduct(@PathVariable("id_product") id: String): WebResponse<ProductResponse> {
        val productResponse = productService.get(id)
        return WebResponse(
                code = 200,
                status = "OK",
                data = productResponse
        )
    }

    @PutMapping(
            value = ["/api/products/{id_product}"],
            produces = ["application/json"],
            consumes = ["application/json"]
    )
    fun updateProduct(@PathVariable("id_product") id: String,
                      @RequestBody updateProductRequest: UpdateProductRequest): WebResponse<ProductResponse> {
        val productResponse = productService.update(id, updateProductRequest)
        return WebResponse(
                code = 200,
                status = "OK",
                data = productResponse
        )
    }

    @DeleteMapping(
            value = ["/api/products/{id_product}"],
            produces = ["application/json"]
    )
    fun deleteProduct(@PathVariable("id_product") id: String): WebResponse<String> {
        productService.delete(id)
        return WebResponse(
                code = 200,
                status = "OK",
                data = id
        )
    }

    @GetMapping(
            value = ["/api/products"],
            produces = ["application/json"]
    )
    fun listProducts(@RequestParam(value = "size", defaultValue = "10") size: Int,
                     @RequestParam(value = "page", defaultValue = "0") page: Int): WebResponse<List<ProductResponse>> {
        val request = ListProductRequest(page = page, size = size)
        val responses = productService.list(request)
        return WebResponse(
                code = 200,
                status = "OK",
                data = responses
        )
    }
}