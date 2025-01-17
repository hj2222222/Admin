package com.catchmind.admin.service;

import com.catchmind.admin.model.entity.BistroDetail;
import com.catchmind.admin.model.entity.BistroInfo;
import com.catchmind.admin.model.entity.ResAdmin;
import com.catchmind.admin.model.network.Header;
import com.catchmind.admin.model.network.request.BisDetailApiRequest;
import com.catchmind.admin.model.network.response.BisDetailApiResponse;
import com.catchmind.admin.model.network.response.BisInfoApiResponse;
import com.catchmind.admin.repository.BisDetailRepository;
import com.catchmind.admin.repository.BistroInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BisDetailLogicService extends BaseService<BisDetailApiRequest, BisDetailApiResponse, BistroDetail> {
    private final BistroInfoRepository bistroInfoRepository;
    private final BisDetailRepository bisDetailRepository;

    private BisDetailApiResponse response(BistroDetail bistroDetail) {
        BisDetailApiResponse bisDetailApiResponse = BisDetailApiResponse.builder()
                .bdIdx(bistroDetail.getBdIdx())
                .bdNotice(bistroDetail.getBdNotice())
                .bdPark(bistroDetail.getBdPark())
                .bdAddr(bistroDetail.getBdAddr())
                .bdHp(bistroDetail.getBdHp())
                .bdIntro(bistroDetail.getBdIntro())
                .bdCaution(bistroDetail.getBdCaution())
                .bdHour(bistroDetail.getBdHour())
                .bdHoliday(bistroDetail.getBdHoliday())
                .bdHome(bistroDetail.getBdHome())
                .resaBisName(bistroDetail.getResAdmin().getResaBisName())
                .bisIdx(bistroDetail.getBistroInfo().getBisIdx())
                .build();
        return bisDetailApiResponse;
    }

    @Override
    public Header<BisDetailApiResponse> create(Header<BisDetailApiRequest> request) {
        BisDetailApiRequest bisDetailApiRequest = request.getData();
        String resaBisName = bisDetailApiRequest.getResaBisName();
        bistroInfoRepository.findByResAdmin_ResaBisName(resaBisName).ifPresent(
                searchUser -> bisDetailApiRequest.setBisIdx(searchUser.getBisIdx())
        );
        System.out.println(bisDetailApiRequest.getBisIdx());
        System.out.println(bisDetailApiRequest.getResaBisName());
        BistroDetail bistroDetail = BistroDetail.builder()
                .resAdmin(ResAdmin.builder().resaBisName(bisDetailApiRequest.getResaBisName()).build())
                .bistroInfo(BistroInfo.builder().bisIdx(bisDetailApiRequest.getBisIdx()).build())
                .build();
        BistroDetail newBisDetail = bisDetailRepository.save(bistroDetail);
        return Header.OK(response(newBisDetail));
    }

    @Override
    public Header<BisDetailApiResponse> read(Long id) {
        return null;
    }

    @Override
    public Header<BisDetailApiResponse> update(Header<BisDetailApiRequest> request) {
        return null;
    }

    @Override
    public Header<BisDetailApiResponse> delete(Long id) {
        return null;
    }
}
