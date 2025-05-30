package ru.smartbudject.crmbackend.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.smartbudject.crmbackend.model.entity.MenuItem;
import ru.smartbudject.crmbackend.service.menu.MenuItemService;
import ru.smartbudject.crmbackend.service.menu.MenuService;
import ru.smartbudject.crmbackend.service.menu.ServiceProcessException;

import java.util.Optional;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Controller
@RequestMapping("/menu/menuItem")
public class MenuItemController {

    final MenuItemService menuItemService;
    final MenuService menuService;


    @ModelAttribute("new_menuItem")
    public MenuItem getNewMeuItem(){
        return new MenuItem();
    }

    @GetMapping("/{menuId}")
    public String getMenuItems(@PathVariable Long menuId, Model model) {
        try {
            model.addAttribute("menuItemsList", menuItemService.getMenuItems(menuId));
            model.addAttribute("menu", menuService.getMenuById(menuId));
        } catch (ServiceProcessException e) {
            model.addAttribute("error", e.getMessage());
        }
        model.addAttribute("menuId", menuId);
        return "menuItems";
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping(value = "/create")
    public String saveMenuItem(
            @RequestParam("menuId") Long menuId,
            @ModelAttribute("new_menuItem") MenuItem menuItem,
            @RequestParam(name = "file") MultipartFile pictureFile,
            Model model
    ) {
        try {
            menuItemService.saveMenuItem(menuId, menuItem, Optional.ofNullable(pictureFile));
        } catch (ServiceProcessException e) {
            model.addAttribute("error", e.getMessage());
        }
        return String.format("redirect:/menu/menuItem/%d", menuId);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/delete")
    public String deleteMenuItem(
            @RequestParam("menuId") Long menuId,
            @RequestParam Long menuItemId,
            Model model
    ) {
        model.addAttribute("menu", menuService.getMenuById(menuId));
        try {
            menuItemService.deleteMenuItem(menuItemId);
        } catch (ServiceProcessException e) {
            model.addAttribute("error", e.getMessage());
        }
        return String.format("redirect:/menu/menuItem/%d", menuId);
    }

//    @ResponseBody
//    @GetMapping("/menuItemList")
//    public ResponseEntity<?> getMenuList(@RequestParam Long menuId) {
//        try {
//            List<MenuItem> menuItems = menuItemService.getMenuItems(menuId);
//            return ResponseEntity
//                    .ok(menuItems);
//        } catch (ServiceProcessException e) {
//            return ResponseEntity.badRequest()
//                    .body(e.getMessage());
//        }
//    }

//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
//    @ResponseBody
//    @PostMapping("/delete")
//    public ResponseEntity<?> deleteMenu(@RequestBody MenuItem menuItem) {
//        try {
//            menuItemService.deleteMenuItem(menuItem);
//            return ResponseEntity
//                    .ok().build();
//        } catch (ServiceProcessException e) {
//            return ResponseEntity
//                    .badRequest()
//                    .body(e.getMessage());
//        }
//    }

//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
//    @ResponseBody
//    @PostMapping(value = "/create")
//    public ResponseEntity<?> saveMenuItem(@RequestPart("menuItem") MenuItem menuItem,
//                                          @RequestPart(required = false, name = "file") MultipartFile pictureFile) {
//        try {
//            MenuItem savedMenu = menuItemService.saveMenuItem(menuItem, Optional.ofNullable(pictureFile));
//            return ResponseEntity
//                    .ok(savedMenu);
//        } catch (ServiceProcessException e) {
//            return ResponseEntity
//                    .badRequest()
//                    .body(e.getMessage());
//        }
//    }

//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
//    @ResponseBody
//    @PostMapping("/edit")
//    public ResponseEntity<?> editMenuItem(@RequestPart("menuItem") MenuItem menuItem,
//                                          @RequestPart(required = false, name = "file") MultipartFile pictureFile) {
//        try {
//            MenuItem savedMenu = menuItemService.editMenuItem(menuItem, Optional.ofNullable(pictureFile));
//            return ResponseEntity
//                    .ok(savedMenu);
//        } catch (ServiceProcessException e) {
//            return ResponseEntity
//                    .badRequest()
//                    .body(e.getMessage());
//        }
//    }


}
