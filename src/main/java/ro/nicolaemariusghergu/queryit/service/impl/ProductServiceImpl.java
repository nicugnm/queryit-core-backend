package ro.nicolaemariusghergu.queryit.service.impl;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import ro.nicolaemariusghergu.queryit.model.Product;
import ro.nicolaemariusghergu.queryit.repository.ProductRepository;
import ro.nicolaemariusghergu.queryit.service.ProductService;

import java.util.List;
import java.util.Optional;

@Service
public record ProductServiceImpl(ProductRepository productRepository) implements ProductService {

    @NonNull
    @Override
    public Optional<Product> findById(@NonNull Long id) {
        return productRepository.findById(id);
    }

    @NonNull
    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product update(Product product) {
        Optional<Product> productAlreadyExist = findById(product.getId());
        Product modifiedProduct = productAlreadyExist
                .map(updatedProduct -> {
                    updatedProduct.setQuantity(product.getQuantity());
                    return updatedProduct;
                })
                .get();
        save(modifiedProduct);
        return modifiedProduct;
    }

    @Override
    public Optional<Product> findByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public List<Product> findAllByPrice(Double price) {
        return productRepository.findAllByPrice(price);
    }

    @Override
    public <S extends Product> S save(S entity) {
        return productRepository.save(entity);
    }

    @Override
    public <S extends Product> List<S> saveAll(Iterable<S> entities) {
        return productRepository.saveAll(entities);
    }

    @NonNull
    @Override
    public void deleteById(@NonNull Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> findAllByCategoryId(Long categoryId) {
        return productRepository.findAllByCategoryId(categoryId);
    }

}
