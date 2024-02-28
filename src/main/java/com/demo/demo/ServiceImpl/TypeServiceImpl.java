package com.demo.demo.ServiceImpl;

import com.demo.demo.Repository.TypeRepository;
import com.demo.demo.Service.TypeService;
import com.demo.demo.dtos.TypeDto;
import com.demo.demo.entity.Type;
import com.demo.demo.mappers.TypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class TypeServiceImpl  implements TypeService {
    private final TypeRepository typeRepository;
    private final TypeMapper typeMapper;

    public TypeServiceImpl(TypeRepository typeRepository, TypeMapper typeMapper) {
        this.typeRepository = typeRepository;
        this.typeMapper = typeMapper;
    }

    @Override
    public Type getTypeById(Long id) {
        return typeRepository.findById(id).orElse(null);
}
@Override
    public List<TypeDto> getAllTypes() {
        return typeRepository.findAll().stream()
                .map(typeMapper::toTypeDTo)
                .collect(Collectors.toList());
    }
    @Override
    public TypeDto createType(TypeDto typeDTO) {
        return typeMapper.toTypeDTo(typeRepository.save(typeMapper.ToType(typeDTO)));
    }
    @Override
    public TypeDto updateType(Long id, TypeDto typeDTO) {
        Optional<Type> existingType = typeRepository.findById(id);
        if (existingType.isPresent()) {
            typeMapper.updateTypeFromDTO(typeDTO, existingType.get());
            return typeMapper.toTypeDTo(typeRepository.save(existingType.get()));
        }
        return null;
    }

    @Override
    public void deleteType(Long id) {
        try {
            typeRepository.deleteById(id);
            System.out.println("Type deleted successfully with ID: " + id);
        } catch (EmptyResultDataAccessException e) {
            System.out.println("Type with ID " + id + " not found");
            throw e;
        } catch (Exception e) {
            System.err.println("Error deleting type");
            throw e;
        }
    }

    @Override
    public void deleteAllTypes() {
        try {
            typeRepository.deleteAll();
            System.out.println("All types deleted successfully");
        } catch (Exception e) {
            System.err.println("Error deleting all types");
            throw e;
        }
    }

}
