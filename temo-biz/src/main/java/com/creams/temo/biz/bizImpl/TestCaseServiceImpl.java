package com.creams.temo.biz.bizImpl;

import com.creams.temo.biz.TestCaseService;

import com.creams.temo.entity.Saves;
import com.creams.temo.entity.TestCase;
import com.creams.temo.entity.Verify;
import com.creams.temo.mapper.SavesMapper;
import com.creams.temo.mapper.TestCaseMapper;
import com.creams.temo.mapper.VerifyMapper;

import com.creams.temo.tools.StringUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class TestCaseServiceImpl implements TestCaseService {

    @Autowired
    TestCaseMapper testCaseMapper;

    @Autowired
    VerifyMapper verifyMapper;

    @Autowired
    SavesMapper savesMapper;

    /**
     * 新增用例信息
     * @param testCaseRequest
     * @return
     */
    @Transactional
    public String addTestCase(TestCase testCaseRequest){
        String caseId = StringUtil.uuid();
        String verifyId = StringUtil.uuid();
        String savesId = StringUtil.uuid();
        testCaseRequest.setCaseId(caseId);
        List<Saves> savesRequests = testCaseRequest.getSaves();
        List<Verify> verifyRequests = testCaseRequest.getVerify();
        if (savesRequests!=null){
            for (Saves s: savesRequests
            ) {
                s.setCaseId(caseId);
                s.setSaveId(savesId);
                savesMapper.addSaves(s);
            }

        }
        if (verifyRequests!=null){
            for (Verify v: verifyRequests
            ) {
                v.setCaseId(caseId);
                v.setVerifyId(verifyId);
                verifyMapper.addVerify(v);
            }
        }

        Integer maxSorting = testCaseMapper.queryMaxSorting(testCaseRequest.getSetId());
        if (maxSorting!=null){
            testCaseRequest.setSorting(maxSorting+1);
        }else{
            testCaseRequest.setSorting(1);
        }
        testCaseMapper.addTestCase(testCaseRequest);

        return caseId;
    }

    /**
     * 修改用例集
     * @param testCaseRequest
     * @return
     */
    @Transactional(rollbackFor=Exception.class)
    public boolean updateTestCase(TestCase testCaseRequest){
        String savesId = StringUtil.uuid();
        String verifyId = StringUtil.uuid();
        String caseId = testCaseRequest.getCaseId();
        if (!caseId.isEmpty()){
            //修改用例信息
            testCaseMapper.updateTestCaseById(testCaseRequest);
            //清除先前的数据，重新插入
            savesMapper.deleteSaves(caseId);
            verifyMapper.deleteVerify(caseId);
            //获取关联参数
            List<Saves> savesRequests = testCaseRequest.getSaves();
            List<Verify> verifyRequests = testCaseRequest.getVerify();
            if (savesRequests!=null){
                for (Saves s: savesRequests
                ) {
                    s.setCaseId(caseId);
                    s.setSaveId(savesId);
                    savesMapper.addSaves(s);
                }

            }
            if (verifyRequests!=null){
                for (Verify v: verifyRequests
                ) {
                    v.setCaseId(caseId);
                    v.setVerifyId(verifyId);
                    verifyMapper.addVerify(v);
                }
            }
            return true;
        }else {
            return false;
        }
    }

    /**
     * 删除用例
     * @param caseId
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public void   deleteTestCase(String caseId){
        testCaseMapper.deleteTestCase(caseId);
        verifyMapper.deleteVerify(caseId);
        savesMapper.deleteSaves(caseId);
    }

    /**
     * 复制用例
     * @param caseId
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean copyTestCase(String caseId){
        TestCase testCaseRequest = testCaseMapper.queryTestCaseById(caseId);
        if (StringUtils.isEmpty(testCaseRequest)){
            return false;
        }else {
            Integer maxSort = testCaseMapper.queryMaxSorting(testCaseRequest.getSetId());
            String testCaseId = StringUtil.uuid();
            testCaseRequest.setCaseId(testCaseId);
            testCaseRequest.setSorting(maxSort + 1);
             testCaseMapper.addTestCase(testCaseRequest);
             return true;
        }
    }

    /**
     * 统计个人用例个数
     * @param userId
     * @return
     */
    public Integer statisticsTestCaseByUserId(String userId){
       return  testCaseMapper.statisticsTestCaseByUserId(userId);
    }


    /**
     * 查询用例
     * @param page
     * @param caseId
     * @param envId
     * @param setId
     * @param caseDesc
     * @param dbId
     * @param caseType
     * @return
     */
    public PageInfo<TestCase> queryTestCase(Integer page, String caseId, String envId, String setId,
                                                    String caseDesc, String dbId, String caseType){
        PageHelper.startPage(page, 10);
        List<TestCase> testCaseResponses = testCaseMapper.queryTestCase(caseId, envId, setId, caseDesc, dbId, caseType);
        PageInfo<TestCase> pageInfo = new PageInfo<>(testCaseResponses);
        pageInfo.getList().forEach(n->n.setSaves(savesMapper.querySaves( caseId)));
        pageInfo.getList().forEach(n->n.setVerify(verifyMapper.queryVerify(caseId)));
        return new PageInfo<>(testCaseResponses);
    }

    /**
     * 查询用例详情
     * @param id
     * @return
     */
    public TestCase queryTestCaseInfo(String id){
        TestCase testCaseResponse = testCaseMapper.queryTestCaseById(id);
        testCaseResponse.setSaves(savesMapper.querySaves(id));
        testCaseResponse.setVerify(verifyMapper.queryVerify(id));
        return testCaseResponse;
    }


    /**
     * 根据用例id查询用例
     * @param caseId
     * @return
     */
    public TestCase queryTestCaseById(String caseId){
        TestCase testCaseResponse = testCaseMapper.queryTestCaseById(caseId);
        return testCaseResponse;
    }

    /**
     * 修改用例排序
     * @param caseId
     * @param move
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean updateTestCaseOrderById(String caseId, String move){

        TestCase testCaseResponse = testCaseMapper.queryTestCaseById(caseId);

        if (testCaseResponse != null && "up".equals(move)){
            Integer sorting = testCaseResponse.getSorting();
            if (sorting.equals(testCaseMapper.queryMinSorting(testCaseResponse.getSetId()))) {
                return false;
            }else {
                //获取当前排序上一位的用例信息
                TestCase result = testCaseMapper.queryTestCaseUpBySorting(testCaseResponse.getSetId(), sorting);
                //更新上一位用例排序
                testCaseMapper.updateTestCaseOrderById(result.getCaseId(), sorting);
                //更新当前用例排序
                testCaseMapper.updateTestCaseOrderById(caseId, result.getSorting());
            }
        }else if (testCaseResponse != null && "down".equals(move)){
            //获取该用例的排序值
            Integer sorting = testCaseResponse.getSorting();

            if (sorting.equals(testCaseMapper.queryMaxSorting(testCaseResponse.getSetId()))){
                return false;
            }else {

                //获取当前排序下一位的用例信息
                TestCase result = testCaseMapper.queryTestCaseDownBySorting(testCaseResponse.getSetId(), sorting);
                //更新下一位用例排序
                testCaseMapper.updateTestCaseOrderById(result.getCaseId(), sorting);
                //更新当前用例排序
                testCaseMapper.updateTestCaseOrderById(caseId, result.getSorting());

            }
        }
        return true;
    }
}
