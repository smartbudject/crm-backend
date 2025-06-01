package ru.smartbudject.crmbackend.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.smartbudject.crmbackend.model.entity.Menu;
import ru.smartbudject.crmbackend.service.menu.MenuService;
import ru.smartbudject.crmbackend.service.menu.ServiceProcessException;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Controller
@RequestMapping("/menu")
public class MenuController {

    final MenuService menuService;

    @ModelAttribute("menuList")
    public List<Menu> fillModelWithMenuList() {
        return menuService.getAllMenus();
    }

    @ModelAttribute("menu")
    public Menu fillModelWithMenu() {
        return new Menu();
    }

    @GetMapping
    public String getMenuPage() {
        return "menus";
    }

    @ResponseBody
    @GetMapping("/menuList")
    public List<Menu> getMenuList() {
        return menuService.getAllMenus();
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/delete")
    public String deleteMenu(
            @RequestParam Long menuId,
            Model model) {
        try {
            menuService.deleteMenu(menuId);
        } catch (ServiceProcessException e) {
            model.addAttribute("error", e.getMessage());
        }
        return "redirect:/menu";
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping(value = "/create")
    public String saveMenu(
            @ModelAttribute("menu") Menu menu,
            @RequestParam(name = "file") MultipartFile pictureFile,
            Model model
    ) {
        try {
            menuService.saveMenu(menu, Optional.ofNullable(pictureFile));
        } catch (ServiceProcessException e) {
            model.addAttribute("error", e.getMessage());
        }
        return "redirect:/menu";
    }

//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
//    @ResponseBody
//    @PostMapping(value = "/create")
//    public ResponseEntity<?> saveMenu(@RequestPart("menu") Menu menu,
//                                      @RequestPart(required = false, name = "file") MultipartFile pictureFile) {
//        try {
//            Menu savedMenu = menuService.saveMenu(menu, Optional.ofNullable(pictureFile));
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
//    public ResponseEntity<?> editMenu(@RequestPart("menu") Menu menu,
//                                      @RequestPart(required = false, name = "file") MultipartFile pictureFile) {
//        try {
//            Menu savedMenu = menuService.editMenu(menu, Optional.ofNullable(pictureFile));
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
//    @PostMapping("/delete")
//    public ResponseEntity<?> deleteMenu(@RequestParam Menu menu) {
//        try {
//            menuService.deleteMenu(menu);
//            return ResponseEntity
//                    .ok().build();
//        } catch (ServiceProcessException e) {
//            return ResponseEntity
//                    .badRequest()
//                    .body(e.getMessage());
//        }
//    }

}

