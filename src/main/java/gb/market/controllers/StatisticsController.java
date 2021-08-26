package gb.market.controllers;

import gb.market.dto.CategoryDto;
import gb.market.services.CategoryService;
import gb.market.utils.StatisticsAspect;
import gb.market.utils.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/statistics")
public class StatisticsController {
    private final StatisticsService statisticsService;
    private final StatisticsAspect statisticsAspect;

    @GetMapping
    public /*Map<String, Long>*/ void showStatistics() {
        statisticsService.testServices();
//        return statisticsAspect.getServiceStatistics();
    }

}
