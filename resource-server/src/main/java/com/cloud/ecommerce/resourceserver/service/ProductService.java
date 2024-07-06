package com.cloud.ecommerce.resourceserver.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.cloud.ecommerce.resourceserver.dto.ProductResponseDto;
import com.cloud.ecommerce.resourceserver.dto.ProductResponseListDto;
import com.cloud.ecommerce.resourceserver.model.Product;
import com.cloud.ecommerce.resourceserver.repository.ProductRepository;
import com.cloud.ecommerce.resourceserver.specification.ProductSpecParams;
import com.cloud.ecommerce.resourceserver.specification.ProductSpecificationTitleBrandCategory;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements IProductService {

	@Value("${pagination.page.size.default}")
	private Integer defaultPageSize;

	private final ProductRepository productRepository;
	private final ProductSpecificationTitleBrandCategory productSpecification;

    public ProductService(ProductRepository productRepository, ProductSpecificationTitleBrandCategory productSpecification) {
        this.productRepository = productRepository;
        this.productSpecification = productSpecification;
    }

    @Override
	public ProductResponseDto getProductById(long id) {
		Optional<Product> productOpt = productRepository.findById(id);
		if (productOpt.isPresent()) {
			ProductResponseDto prdto = new ProductResponseDto();
			prdto.populateDto(productOpt.get());
			return prdto;
		}
		return null;
	}

    @Override
    public ProductResponseListDto getProductList(ProductSpecParams specParams) {
        List<Product> productList;
        Page<Product> pages;

        if (Integer.valueOf(specParams.getPageIndex()) == null || specParams.getPageIndex() == 0) {
            productList = productRepository.findAll(productSpecification.getProducts(specParams));
            if(productList != null && !productList.isEmpty()) {
                ProductResponseListDto prldto =  new ProductResponseListDto();
                prldto.setTotalCount(productList.size());
                prldto.setProductList(new ArrayList<>());
                for(Product product: productList) {

                    ProductResponseDto prdto = new ProductResponseDto();
                    prdto.populateDto(product);
                    prldto.getProductList().add(prdto);
                }
                return prldto;
            }

        }
        else {
            if(specParams.getPageSize() == 0) {

                specParams.setPageSize(defaultPageSize);

            }
            Pageable paging = PageRequest.of(specParams.getPageIndex()-1, specParams.getPageSize());
            pages = productRepository.findAll(productSpecification.getProducts(specParams), paging);
            if(pages != null && pages.getContent() != null) {

                productList = pages.getContent();
                if(productList != null && !productList.isEmpty()) {
                    ProductResponseListDto   prldto = new ProductResponseListDto();
                    prldto.setTotalPages(pages.getTotalPages());
                    prldto.setTotalCount(pages.getTotalElements());
                    prldto.setPageIndex(pages.getNumber() +1);
                    prldto.setPageSize(specParams.getPageSize());
                    prldto.setProductList(new ArrayList<ProductResponseDto>());
                    for(Product product: productList) {

                        ProductResponseDto prdto = new ProductResponseDto();
                        prdto.populateDto(product);
                        prldto.getProductList().add(prdto);
                    }
                    return prldto;
                }
            }
        }
        return null;
    }

}
