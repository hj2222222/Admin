package com.catchmind.admin.service;

import com.catchmind.admin.model.entity.Point;

import com.catchmind.admin.model.network.Header;
import com.catchmind.admin.model.network.request.PointApiRequest;

import com.catchmind.admin.model.network.response.PointApiResponse;
import com.catchmind.admin.repository.PointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PointApiLogicService extends BaseService<PointApiRequest, PointApiResponse, Point>{

    private final PointRepository pointRepository;

    private PointApiResponse response(Point points) {
        PointApiResponse pointApiResponse = PointApiResponse.builder()
                .poIdx(points.getPoIdx())
                .prIdx(points.getPrIdx())
                .poList(points.getPoList())
                .regDate(points.getRegDate())
                .updateDate(points.getUpdateDate())
                .build();
        return pointApiResponse;
    }

    @Override
    public Header<PointApiResponse> create(Header<PointApiRequest> request) {
        return null;
    }

    @Override
    public Header<PointApiResponse> read(Long id) {
        return null;
    }

    @Override
    public Header<PointApiResponse> update(Header<PointApiRequest> request) {
        return null;
    }

    @Override
    public Header<PointApiResponse> delete(Long id) {
        return null;
    }

    public Header<PointApiResponse> createlist(Long id, String list){
        Point point = Point.builder().poList(list).prIdx(id).build();
        Point point1 = baseRepository.save(point);
        return Header.ok();
    }
}
