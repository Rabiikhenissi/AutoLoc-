package tn.esprit.autoloc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Maintenance;
import tn.esprit.autoloc.repository.IMaintenanceRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MaintenanceServiceImpl implements IMaintenance {

    private final IMaintenanceRepository maintenanceRepo;

    @Override
    public Maintenance getMaintenanceById(Long id) {
        return maintenanceRepo.findById(id).orElse(null);
    }

    @Override
    public Maintenance addMaintenance(Maintenance maintenance) {
        return maintenanceRepo.save(maintenance);
    }

    @Override
    public Maintenance updateMaintenance(Maintenance maintenance) {
        return maintenanceRepo.save(maintenance);
    }

    @Override
    public void deleteMaintenance(Long id) {
        maintenanceRepo.deleteById(id);
    }

    @Override
    public List<Maintenance> getAllMaintenances() {
        return maintenanceRepo.findAll();
    }
}