package ru.smartbudject.crmbackend.service.menu;

import ch.qos.logback.core.util.StringUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.smartbudject.crmbackend.model.dto.MenuDTO;
import ru.smartbudject.crmbackend.model.entity.Menu;
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
public class MenuService {

    final MenuRepository menuRepository;
    final UploadFileUtil uploadFileUtil;


    public List<Menu> getAllMenus() {
        return menuRepository.findAll();
    }

    public MenuDTO getMenuById(Long id) {
        Menu menu = menuRepository.findById(id).orElseGet(null);
        MenuDTO menuDTO = new MenuDTO();
        menuDTO.setId(menu.getId());
        return menuDTO;
    }

    public Menu saveMenu(Menu menu, Optional<MultipartFile> pictureFile) throws ServiceProcessException {
        if (StringUtil.isNullOrEmpty(menu.getName()))
            throw new ServiceProcessException("Menu name is empty!");
        if (pictureFile.isPresent() && !pictureFile.get().isEmpty()) {
            try {
                menu.setPictureFileName(uploadFileUtil.saveFile(pictureFile.get()));
            } catch (IOException e) {
                e.printStackTrace();
                throw new ServiceProcessException("Error on saving pictureFile!");
            }
        }
        return menuRepository.save(menu);
    }

    public void deleteMenu(Menu menu) throws ServiceProcessException {
        if (Objects.isNull(menu.getId()))
            throw new ServiceProcessException("No menu id!");
        menuRepository.deleteById(menu.getId());
    }

    public void deleteMenu(Long menuId) throws ServiceProcessException {
        if (Objects.isNull(menuId))
            throw new ServiceProcessException("No menu id!");
        menuRepository.deleteById(menuId);
    }

    public Menu editMenu(Menu menu, Optional<MultipartFile> pictureFile) throws ServiceProcessException {
        if (Objects.isNull(menu.getId()) || !menuRepository.existsById(menu.getId()))
            throw new ServiceProcessException("Id of menu is empty or it's does'not exists!");
        if (pictureFile.isPresent()) {
            try {
                menu.setPictureFileName(uploadFileUtil.saveFile(pictureFile.get()));
            } catch (IOException e) {
                e.printStackTrace();
                throw new ServiceProcessException("Error on saving pictureFile!");
            }
        }

        return menuRepository.save(menu);
    }

}
