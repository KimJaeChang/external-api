package kr.co.kjc.externalApi.controller;

import kr.co.kjc.externalApi.global.enums.EnumParentExternalApiType;
import kr.co.kjc.externalApi.service.custom.KecoApiServiceRouter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/v1/{external_parent_type}")
@RequiredArgsConstructor
public class KecoApiController {

  private final KecoApiServiceRouter kecoApiServiceRouter;

  @GetMapping("/")
  public void findAllByChargers(@PathVariable("external_parent_type") EnumParentExternalApiType externalParentType) {

  }

}
