package shop.mtcoding.orange.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper // mapper 사용하면 interface인 파일과 product.xml을 연결시켜줌 - xml을 구현한 클래스가 해당 interface를
        // 상속함

public interface ProductRepository {
    public List<Product> findAll(); // findAll = select 값

    public Product findOne(int id);

    // -1 db 에러, 1 변경된 행이 1건 , 0 변경된 행이 없음
    public int insert(@Param("name") String name, @Param("price") int price, @Param("qty") int qty);

    public int delete(@Param("id") int id);

    public int update(@Param("id") int id, @Param("name") String name, @Param("price") int price,
            @Param("qty") int qty);
}
