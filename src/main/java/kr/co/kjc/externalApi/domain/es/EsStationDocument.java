package kr.co.kjc.externalApi.domain.es;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "members")
public class EsStationDocument {

  @Id
  private Long id;

  private String stationName;

}
