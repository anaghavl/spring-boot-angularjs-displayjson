package com.userdata.display.controller;
import com.userdata.display.model.Child;
import com.userdata.display.service.ChildService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/child")
public class ChildController {
    private ChildService childService;
    
    public ChildController(ChildService childService) {
        this.childService = childService;
    }

    /** 
     * API endpoint to call the children when 
     * clicked on total paid amount */
    @GetMapping("/list")
    public List<Child> list(@RequestParam Integer parentId) {
        return childService.list(parentId);
    }

}
