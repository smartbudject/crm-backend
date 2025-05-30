package ru.smartbudject.crmbackend.service.menu;

import ch.qos.logback.core.util.StringUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.smartbudject.crmbackend.model.entity.Menu;
import ru.smartbudject.crmbackend.model.entity.MenuItem;
import ru.smartbudject.crmbackend.repository.MenuItemRepository;
import ru.smartbudject.crmbackend.repository.MenuRepository;
import ru.smartbudject.crmbackend.utils.UploadFileUtil;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Transactional
@Service
public class MenuItemService {

    final MenuItemRepository menuItemRepository;
    final MenuRepository menuRepository;
    final UploadFileUtil uploadFileUtil;

    public List<MenuItem> getMenuItems(Menu menu) throws ServiceProcessException {
        if (Objects.isNull(menu.getId()))
            throw new ServiceProcessException("No menu id!");
        return menuItemRepository.findAllByMenuId(menu.getId());
    }

    public List<MenuItem> getMenuItems(Long menuId) throws ServiceProcessException {
        if (Objects.isNull(menuId))
            throw new ServiceProcessException("No menu id!");
        return menuItemRepository.findAllByMenuId(menuId);
    }

    public MenuItem saveMenuItem(
            Long menuId,
            MenuItem menuItem,
            Optional<MultipartFile> pictureFile
    ) throws ServiceProcessException {

        if (StringUtil.isNullOrEmpty(menuItem.getName()))
            throw new ServiceProcessException("Menu item name is empty!");
        if (Objects.isNull(menuId))
            throw new ServiceProcessException("MenuId of menuItem is empty!");

        try {
            Optional<Menu> menuOptional = menuRepository.findById(menuId);
            if (menuOptional.isEmpty()) throw new ServiceProcessException("Menu not found menuId = " + menuId);
            menuItem.setMenu(menuOptional.get());
            if (pictureFile.isPresent() && !pictureFile.get().isEmpty())
                menuItem.setPictureFileName(uploadFileUtil.saveFile(pictureFile.get()));
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServiceProcessException("Error on saving pictureFile!");
        }

        if (menuItem.getPrice() == null) menuItem.setPrice(0);
        return menuItemRepository.save(menuItem);
    }

    public void deleteMenuItem(MenuItem menuItem) throws ServiceProcessException {
        if (Objects.isNull(menuItem.getId()))
            throw new ServiceProcessException("No menuItem id!");
        menuItemRepository.deleteById(menuItem.getId());
    }

    public void deleteMenuItem(Long menuItemId) throws ServiceProcessException {
        if (Objects.isNull(menuItemId))
            throw new ServiceProcessException("No menuItem id!");
        menuItemRepository.deleteById(menuItemId);
    }

    public MenuItem editMenuItem(MenuItem menuItem,
                                 Optional<MultipartFile> pictureFile) throws ServiceProcessException {
        if (Objects.isNull(menuItem.getId()) || !menuItemRepository.existsById(menuItem.getId()))
            throw new ServiceProcessException("Id of menuItem is empty or it's does'not exists!");
        if (pictureFile.isPresent()) {
            try {
                menuItem.setPictureFileName(uploadFileUtil.saveFile(pictureFile.get()));
            } catch (IOException e) {
                e.printStackTrace();
                throw new ServiceProcessException("Error on saving pictureFile!");
            }
        }
        return menuItemRepository.save(menuItem);
    }
}
