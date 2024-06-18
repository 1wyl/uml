package com.example.backend.relative;

//public class RelativeService {
//}
import com.example.backend.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class RelativeService {
    private RelativeMapper relativeMapper;

    public RelativeService(RelativeMapper relativeMapper) {
        this.relativeMapper = relativeMapper;
    }

    public void addRelative(Relative relative) {
        relativeMapper.addRelative(relative);
    }

    public List<Relative> getAllRelatives(int id) {
        return relativeMapper.getAllRelatives(id);
    }
    public Relative findRelativeByRId(int rid) {
        return relativeMapper.findRelativeByRid(rid);
    }
    @Transactional
    public void updateRelative(Relative relative){
        try {

            relativeMapper.updateRelative(relative);
        } catch (Exception e) {
            System.out.println("插入任务时出现异常：" + e.getMessage());
            e.printStackTrace();
        }
    }
}
