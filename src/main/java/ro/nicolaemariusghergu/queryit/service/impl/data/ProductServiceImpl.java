package ro.nicolaemariusghergu.queryit.service.impl.data;

import com.fasterxml.jackson.databind.json.JsonMapper;
import org.jeasy.random.randomizers.number.IntegerRandomizer;
import org.jeasy.random.randomizers.range.IntegerRangeRandomizer;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;
import ro.nicolaemariusghergu.queryit.dto.ProductDto;
import ro.nicolaemariusghergu.queryit.executor.ExecutorHandler;
import ro.nicolaemariusghergu.queryit.model.Shelf;
import ro.nicolaemariusghergu.queryit.model.data.Product;
import ro.nicolaemariusghergu.queryit.model.data.ShelfCategory;
import ro.nicolaemariusghergu.queryit.repository.ProductRepository;
import ro.nicolaemariusghergu.queryit.service.data.ProductService;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Executor;

@Service
public record ProductServiceImpl(ProductRepository productRepository) implements ProductService {

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public void handleDataFromWeb() throws JSONException, IOException {
        // hardcoded website to get mega image products from promotions, I need some data , lol , i stole some from you guys, sorry
        String baseLink = "https://api.mega-image.ro/?operationName=GetCategoryProductSearch&variables=%7B%22lang%22%3A%22ro%22%2C%22searchQuery%22%3A%22%22%2C%22category%22%3A%220--%22%2C%22pageNumber%22%3A0%2C%22pageSize%22%3A20%2C%22filterFlag%22%3Atrue%7D&extensions=%7B%22persistedQuery%22%3A%7B%22version%22%3A1%2C%22sha256Hash%22%3A%22bdb32b6dc09b8ee0bce785e0a6799c6ff2593a8d1fd2a4ad2ac66cf3e999fdf5%22%7D%7D";
        String baseLinkImages = "https://d1lqpgkqcok0l.cloudfront.net";

        // deoarece avem 15 categorii
        for (int i = 1; i <= 15; i++) {
            String word;
            if (i >= 10) {
                word = "" + i;
            } else {
                word = "0" + i;
            }

            System.out.println("Avem site-ul numarul " + i);
            String dataLink = baseLink.replace("--", word);
            System.out.println("Site-ul este " + dataLink);
            JSONObject jsonObject = readJsonFromUrl(dataLink);

            JSONArray jsonArray = jsonObject.getJSONObject("data").getJSONObject("categoryProductSearch").getJSONArray("products");

            String category = jsonObject.getJSONObject("data")
                    .getJSONObject("categoryProductSearch")
                    .getJSONArray("categorySearchTree")
                    .getJSONObject(0)
                    .getJSONArray("categoryDataList")
                    .getJSONObject(0)
                    .getJSONObject("categoryData")
                    .getJSONObject("facetData")
                    .get("name")
                    .toString();

            ExecutorHandler.downloadData(dataLink, category);

            ShelfCategory shelfCategory = new ShelfCategory();
            shelfCategory.setName(category);

            System.out.println();
            System.out.println("Pentru categoria " + category + " avem urmatoarele produse:");
            for (int j = 0; j < jsonArray.length(); j++) {
                String productName = jsonArray
                        .getJSONObject(j)
                        .getString("name");

                Double price = jsonArray
                        .getJSONObject(j)
                        .getJSONObject("price")
                        .getDouble("value");

                String manufacturerName = jsonArray
                        .getJSONObject(j)
                        .getString("manufacturerName");

                String urlImage = jsonArray.getJSONObject(j)
                        .getJSONArray("images")
                        .getJSONObject(2) // the correct array for url icon
                        .getString("url");

                String iconUrl = baseLinkImages + urlImage;

                ProductDto productDto = new ProductDto();
                productDto.setName(productName);
                productDto.setPrice(price);
                productDto.setDeliveryData(LocalDateTime.now());
                productDto.setQuantity(new IntegerRangeRandomizer(1, 50).getRandomValue());
                productDto.setIconUrl(iconUrl);
                productDto.setManufacturerName(manufacturerName);

                Shelf shelf = new Shelf();
                shelf.setProduct(productDto);
                shelf.setShelfCategory(shelfCategory);

                System.out.println(productDto);
            }
        }
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }
}
