package com.userdata.display.controller;
import com.userdata.display.model.Parent;
import com.userdata.display.service.ParentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/parents")
public class ParentController {

    private ParentService parentService;
    
    public ParentController(ParentService parentService) {
        this.parentService = parentService;
    }

    /* API endpoint to list all the parent details with page no.(offset) 
     * for pagination and sort order (asc or desc) */
    @GetMapping("/list")
    public List<Parent> list(@RequestParam Integer offset, @RequestParam String order) {
        return parentService.list(offset, order);
    }

    /**
     * API endpoint to get the total number of entries 
     * to calculate the page number */
    @GetMapping("/total")
    public Integer totalEntries() {
        return parentService.totalEntries();
    }

}
