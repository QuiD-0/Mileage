package mileage.mileage_be.history.controller;


import lombok.RequiredArgsConstructor;
import mileage.mileage_be.history.domain.History;
import mileage.mileage_be.history.service.HistoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/history")
@RequiredArgsConstructor
public class HistoryController {

    private final HistoryService historyService;

    @GetMapping
    public List<History> findAllHistory(){
        return historyService.findAllHistory();
    }

    @GetMapping("/{userId}")
    public List<History> findOnesHistory(@PathVariable(name = "userId")String userId){
        return historyService.findAllHistoryByUserId(userId);
    }
}
