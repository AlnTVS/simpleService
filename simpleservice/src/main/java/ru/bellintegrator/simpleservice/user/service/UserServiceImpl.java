package ru.bellintegrator.simpleservice.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.simpleservice.citizenship.dao.CitizenshipDao;
import ru.bellintegrator.simpleservice.common.exception.NotFoundEntityByReceivedParametersException;
import ru.bellintegrator.simpleservice.common.exception.NotFountRequiredParametersException;
import ru.bellintegrator.simpleservice.document.dao.DocumentDao;
import ru.bellintegrator.simpleservice.document.dao.TypeDocumentDao;
import ru.bellintegrator.simpleservice.document.entity.DocumentEntity;
import ru.bellintegrator.simpleservice.document.entity.TypeDocumentEntity;
import ru.bellintegrator.simpleservice.mapper.MapperFacade;
import ru.bellintegrator.simpleservice.office.dao.OfficeDao;
import ru.bellintegrator.simpleservice.position.dao.PositionDao;
import ru.bellintegrator.simpleservice.position.entity.PositionEntity;
import ru.bellintegrator.simpleservice.user.dao.UserDao;
import ru.bellintegrator.simpleservice.user.entity.UserEntity;
import ru.bellintegrator.simpleservice.user.view.UserForHTTPMethodListView;
import ru.bellintegrator.simpleservice.user.view.UserForHTTPMethodSaveView;
import ru.bellintegrator.simpleservice.user.view.UserForHTTPMethodsExtendedView;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final MapperFacade mapperFacade;
    private final UserDao userDao;
    private final PositionDao positionDao;
    private final DocumentDao documentDao;
    private final TypeDocumentDao typeDocumentDao;
    private final OfficeDao officeDao;
    private final CitizenshipDao citizenshipDao;

    @Autowired
    public UserServiceImpl(UserDao dao, MapperFacade mapperFacade, PositionDao positionDao, DocumentDao documentDao, TypeDocumentDao typeDocumentDao, OfficeDao officeDao, CitizenshipDao citizenshipDao) {
        this.userDao = dao;
        this.mapperFacade = mapperFacade;
        this.positionDao = positionDao;
        this.documentDao = documentDao;
        this.typeDocumentDao = typeDocumentDao;
        this.officeDao = officeDao;
        this.citizenshipDao = citizenshipDao;
    }

    @Override
    public List<UserForHTTPMethodListView> users() {
        List<UserEntity> userEntityList = userDao.loadAllUsers();
        return mapperFacade.mapUserEntityToUserViewAsList(userEntityList, UserForHTTPMethodListView.class);
    }

    @Override
    public List<UserForHTTPMethodListView> users(UserForHTTPMethodListView user) {
        if (user.officeId == null) {
            throw new NotFountRequiredParametersException("Parameter 'officeId' is required. But it's null.");
        }
        List<UserEntity> userEntityList = userDao.loadAllUsersByFilter(user);
        if (userEntityList.isEmpty()) {
            throw new NotFoundEntityByReceivedParametersException("DB doesn't contains entity that meets the conditions of filter.");
        }
        return mapperFacade.mapUserEntityToUserViewAsList(userEntityList, UserForHTTPMethodListView.class);
    }

    @Override
    public UserForHTTPMethodsExtendedView userById(Long id) {
        try {
            return mapperFacade.mapUserEntityToUserExtendedView(userDao.loadUserById(id), UserForHTTPMethodsExtendedView.class);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundEntityByReceivedParametersException("User with id = " + id + " doesn't exist.");
        }
    }

    @Transactional
    @Override
    public void updateUser(UserForHTTPMethodsExtendedView user) {
        if (user.id == null ||
                user.firstName == null ||
                user.positions == null) {
            throw new NotFountRequiredParametersException(
                    "user id = " + user.id
                            + "\r\nuser first name = " + user.firstName
                            + "\r\nuser positions = " + user.positions
                            + "\r\nThis parameters must be filled!");
        }
        UserEntity userEntity;
        try {
            userEntity = userDao.loadUserById(Long.valueOf(user.id));
        } catch (EmptyResultDataAccessException e) {
            throw new NotFountRequiredParametersException("User with id = " + user.id + " doesn't exist.");
        }
        userEntity.setFirstName(user.firstName);
        Set<PositionEntity> positionEntities = new HashSet<>();
        for (String position : user.positions) {
            positionEntities.add(positionDao.findByName(position));
        }
        userEntity.setPositions(positionEntities);
        if (user.officeId != null) {
            userEntity.setOffice(officeDao.loadOfficeById(Long.valueOf(user.officeId)));
        }
        if (user.secondName != null) {
            userEntity.setSecondName(user.secondName);
        }
        if (user.middleName != null) {
            userEntity.setMiddleName(user.middleName);
        }
        if (user.phone != null) {
            userEntity.setPhone(user.phone);
        }
        if (user.docName != null) {
            userEntity.getDocument().setTypeDocument(typeDocumentDao.loadByType(user.docName));
        }
        if (user.docNumber != null) {
            userEntity.getDocument().setNumber(user.docNumber);
        }
        if (user.docDate != null) {
            userEntity.getDocument().setDate(user.docDate);
        }
        if (user.citizenshipCode != null) {
            userEntity.setCitizenship(citizenshipDao.loadByCode(user.citizenshipCode));
        }
        userDao.updateUser(userEntity);
    }

    @Transactional
    @Override
    public void saveUser(UserForHTTPMethodSaveView user) {

        if (user.officeId == null ||
                user.firstName == null ||
                user.positions == null) {
            throw new NotFountRequiredParametersException(
                    "user officeId = " + user.officeId
                            + "\r\nuser first name = " + user.firstName
                            + "\r\nuser positions = " + user.positions
                            + "\r\nThis parameters must be filled!");
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setOffice(officeDao.loadOfficeById(Long.valueOf(user.officeId)));
        userEntity.setFirstName(user.firstName);
        Set<PositionEntity> positionEntities = new HashSet<>();
        for (String position : user.positions) {
            positionEntities.add(positionDao.findByName(position));
        }
        userEntity.setPositions(positionEntities);
        if (user.officeId != null) {
            userEntity.setOffice(officeDao.loadOfficeById(Long.valueOf(user.officeId)));
        }
        if (user.secondName != null) {
            userEntity.setSecondName(user.secondName);
        }
        if (user.middleName != null) {
            userEntity.setMiddleName(user.middleName);
        }
        if (user.phone != null) {
            userEntity.setPhone(user.phone);
        }
        if (user.citizenshipCode != null) {
            userEntity.setCitizenship(citizenshipDao.loadByCode(user.citizenshipCode));
        }
        userEntity.setIsActive(true);
        if (user.docCode != null || user.docNumber != null || user.docDate != null) {
            DocumentEntity document = new DocumentEntity();
            document.setUser(userEntity);
            userEntity.setDocument(document);
            if (user.docCode != null) {
                TypeDocumentEntity typeDocument = typeDocumentDao.loadByCode(user.docCode);
                document.setTypeDocument(typeDocument);
            }
            if (user.docNumber != null) {
                document.setNumber(user.docNumber);
            }
            if (user.docDate != null) {
                document.setDate(user.docDate);
            }
        }
        userDao.saveUser(userEntity);
    }
}
