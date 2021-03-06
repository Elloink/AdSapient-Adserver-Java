/*
 * AdSapient - Open Source Ad Server
 * http://www.sourceforge.net/projects/adsapient
 * http://www.adsapient.com
 *
 * Copyright (C) 2001-06 Vitaly Sazanovich
 * Vitaly.Sazanovich@gmail.com
 *
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Library General Public License  as published by the
 * Free Software Foundation; either version 2 of the License, or (at your
 * option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 *
 */
package com.adsapient.adserver;

import com.adsapient.adserver.beans.AdserverModel;
import com.adsapient.adserver.beans.TotalsReporterModel;
import com.adsapient.adserver.reporter.ReporterModelBuilder;
import com.adsapient.api.FilterInterface;
import com.adsapient.api.IMappable;
import com.adsapient.shared.AdsapientConstants;
import com.adsapient.shared.dao.HibernateEntityDao;
import com.adsapient.shared.mappable.*;
import com.adsapient.shared.service.BannerManagementService;
import com.adsapient.shared.service.FiltersService;
import org.apache.log4j.Logger;

import java.util.*;

public class ModelUpdater {
    static Logger logger = Logger.getLogger(ModelUpdater.class);

    private AdserverModel adserverModel;

    private HibernateEntityDao hibernateEntityDao;

    private BannerManagementService bannerManagementService;

    private ReporterModelBuilder reporterModelBuilder;

    private TotalsReporterModel totalsReporterModel;

    public ReporterModelBuilder getReporterModelBuilder() {
        return reporterModelBuilder;
    }

    public void setReporterModelBuilder(
            ReporterModelBuilder reporterModelBuilder) {
        this.reporterModelBuilder = reporterModelBuilder;
    }

    public void setup() {
        List<IMappable> users = (List<IMappable>) hibernateEntityDao
                .viewAll(UserImpl.class);
        adserverModel.setUsersMap(generateMapFromList(users));

        List<IMappable> banners = (List<IMappable>) hibernateEntityDao
                .viewAll(BannerImpl.class);
        adserverModel.setBannersMap(generateMapFromList(banners));

        List<IMappable> campaigns = (List<IMappable>) hibernateEntityDao
                .viewAll(CampainImpl.class);
        adserverModel.setCampaignsMap(generateMapFromList(campaigns));

        List<IMappable> places = (List<IMappable>) hibernateEntityDao
                .viewAll(PlacesImpl.class);
        adserverModel.setPlacesMap(generateMapFromList(places));

        List<IMappable> sites = (List<IMappable>) hibernateEntityDao
                .viewAll(SiteImpl.class);
        adserverModel.setSitesMap(generateMapFromList(sites));

        List<IMappable> rates = (List<IMappable>) hibernateEntityDao
                .viewAll(RateImpl.class);
        adserverModel.setRatesMap(generateMapFromList(rates));

        List<IMappable> options = (List<IMappable>) hibernateEntityDao
                .viewAll(AdminOptions.class);
        adserverModel.setOptionsMap(generateMapFromList(options));
        adserverModel.rebuildOptionsItemNameToItemValueMap();

        List<IMappable> sizes = (List<IMappable>) hibernateEntityDao
                .viewAll(Size.class);
        adserverModel.setSizesMap(generateMapFromList(sizes));

        List<FilterInterface>timeFilters = (List<FilterInterface>) hibernateEntityDao.viewAll(TimeFilter.class);
        adserverModel.setFiltersToCampaignsAndBanners(timeFilters);
        List<FilterInterface>dateFilters = (List<FilterInterface>) hibernateEntityDao.viewAll(DateFilter.class);
        adserverModel.setFiltersToCampaignsAndBanners(dateFilters);
        List<FilterInterface>systemsFilters = (List<FilterInterface>) hibernateEntityDao.viewAll(SystemsFilter.class);
        adserverModel.setFiltersToCampaignsAndBanners(systemsFilters);
        List<FilterInterface>parametersFilters = (List<FilterInterface>) hibernateEntityDao.viewAll(ParametersFilter.class);
        adserverModel.setFiltersToCampaignsAndBanners(parametersFilters);
        List<FilterInterface>keywordsFilters = (List<FilterInterface>) hibernateEntityDao.viewAll(KeywordsFilter.class);
        adserverModel.setFiltersToCampaignsAndBanners(keywordsFilters);
        List<FilterInterface>behaviorFilters = (List<FilterInterface>) hibernateEntityDao.viewAll(BehaviorFilter.class);
        adserverModel.setFiltersToCampaignsAndBanners(behaviorFilters);
        List<FilterInterface>geoFilters = (List<FilterInterface>) hibernateEntityDao.viewAll(GeoFilter.class);
        adserverModel.setFiltersToCampaignsAndBanners(geoFilters);
        List<FilterInterface>trafficFilters = (List<FilterInterface>) hibernateEntityDao.viewAll(TrafficFilter.class);
        adserverModel.setFiltersToCampaignsAndBanners(trafficFilters);
        List<FilterInterface>contentFilters = (List<FilterInterface>) hibernateEntityDao.viewAll(ContentFilter.class);
        adserverModel.setFiltersToCampaignsAndBanners(contentFilters);
        List<IMappable>parameters = (List<IMappable>) hibernateEntityDao.viewAll(ParameterImpl.class);
        adserverModel.setParametersMap(generateMapFromList(parameters));
        List<IMappable>geoLocations = (List<IMappable>) hibernateEntityDao.viewAll(CountryAbbrEntity.class);
        adserverModel.setGeoLocationsMap(generateMapFromList(geoLocations));
        adserverModel.setGeoLocationsReverseMap(generateReverseMapFromGeoLocations(geoLocations));
        List<IMappable>systemSettings = (List<IMappable>) hibernateEntityDao.viewAll(SystemSettings.class);
        Map<String, SystemSettings> browsersMap = new TreeMap<String, SystemSettings>();
        Map<String, SystemSettings> systemsMap = new TreeMap<String, SystemSettings>();
        Map<String, SystemSettings> langsMap = new TreeMap<String, SystemSettings>();

        for (IMappable im : systemSettings) {
            SystemSettings ss = (SystemSettings) im;
            int ti = ss.getTypeid();

            switch (ti) {
                case AdsapientConstants.BROWSER_TYPE_ID:
                    browsersMap.put(ss.getSskey(), ss);

                    break;

                case AdsapientConstants.OS_TYPE_ID:
                    systemsMap.put(ss.getSskey(), ss);

                    break;

                case AdsapientConstants.LANGUAGE_TYPE_ID:
                    langsMap.put(ss.getSskey(), ss);

                    break;
            }
        }

        adserverModel.setBrowsersMap(browsersMap);
        adserverModel.setOssMap(systemsMap);
        adserverModel.setLanguagesMap(langsMap);

        List<CountryAbbrEntity> countries = (List<CountryAbbrEntity>) hibernateEntityDao
                .viewAllWithOrder(CountryAbbrEntity.class, "countryName");
        FiltersService.fillCountryMap(countries);
        repool();
    }

    private Map<String, IMappable> generateReverseMapFromGeoLocations(
            List<IMappable> geoLocations) {
        Map<String, IMappable> m = new HashMap<String, IMappable>();

        for (IMappable im : geoLocations) {
            CountryAbbrEntity cae = (CountryAbbrEntity) im;
            m.put(cae.getCountryAbbr2(), im);
        }

        return m;
    }

    public void repool() {
        bannerManagementService.getBannersFromActiveCampaigns(adserverModel);
        bannerManagementService.getDefaultBanners(adserverModel);
        bannerManagementService.getDefaultPublisherBanners(adserverModel);
    }

    public void update(Class clazz, Integer id,
                       Map<String, Object> requestParams) {
        try {
            long t1 = System.currentTimeMillis();
            IMappable entity = (IMappable) hibernateEntityDao.loadObject(clazz,
                    id);

            if (clazz.getName().equals(UserImpl.class.getName())) {
                if (entity == null) {
                    removeTotalsReport(UserImpl.class, id);
                    adserverModel.getUsersMap().remove(id);
                } else {
                    adserverModel.getUsersMap().put(id, entity);
                }

                return;
            }

            if (clazz.getName().equals(BannerImpl.class.getName())) {
                if (entity == null) {
                    removeTotalsReport(BannerImpl.class, id);
                    if (adserverModel.getDefaultBannersPool().containsKey(id)) {
                        adserverModel.getDefaultBannersPool().remove(id);
                    } else if (adserverModel.getBannersMap().containsKey(id)) {
                        adserverModel.getBannersMap().remove(id);
                    } else if (adserverModel.getDefaultPublisherBannerPool()
                            .containsKey(id)) {
                        adserverModel.getDefaultPublisherBannerPool()
                                .remove(id);
                    }
                } else {
                    int status = ((BannerImpl) entity).getStatus();

                    if (status == BannerImpl.STATUS_DEFAULT) {
                        adserverModel.getDefaultBannersPool().put(id, entity);
                    } else if (status == BannerImpl.STATUS_PUBLISHER_DEFAULT) {
                        adserverModel.getDefaultPublisherBannerPool().put(id,
                                entity);
                    } else {
                        adserverModel.getBannersMap().put(id, entity);
                    }
                }
                repool();
                return;
            }

            if (clazz.getName().equals(AdminOptions.class.getName())) {
                if (entity == null) {
                    adserverModel.getOptionsMap().remove(id);
                } else {
                    adserverModel.getOptionsMap().put(id, entity);
                }

                adserverModel.rebuildOptionsItemNameToItemValueMap();

                return;
            }

            if (clazz.getName().equals(CampainImpl.class.getName())) {
                if (entity == null) {
                    removeTotalsReport(CampainImpl.class, id);
                    adserverModel.getCampaignsMap().remove(id);
                } else {
                    adserverModel.getCampaignsMap().put(id, entity);
                }

                repool();

                return;
            }

            if (clazz.getName().equals(PlacesImpl.class.getName())) {
                if (entity == null) {
                    removeTotalsReport(PlacesImpl.class, id);
                    adserverModel.getPlacesMap().remove(id);
                } else {
                    adserverModel.getPlacesMap().put(id, entity);
                }

                return;
            }

            if (clazz.getName().equals(SiteImpl.class.getName())) {
                if (entity == null) {
                    removeTotalsReport(SiteImpl.class, id);
                    adserverModel.getSitesMap().remove(id);
                } else {
                    adserverModel.getSitesMap().put(id, entity);
                }

                return;
            }

            if (clazz.getName().equals(Size.class.getName())) {
                if (entity == null) {
                    adserverModel.getSizesMap().remove(id);
                } else {
                    adserverModel.getSizesMap().put(id, entity);
                }

                return;
            }

            if (clazz.getName().equals(RateImpl.class.getName())) {
                if (entity == null) {
                    adserverModel.getRatesMap().remove(id);
                } else {
                    adserverModel.getRatesMap().put(id, entity);
                }

                return;
            }

            if (clazz.getName().equals(TotalsReport.class.getName())) {
                reporterModelBuilder
                        .resetTotalsReport(clazz, id, requestParams);

                return;
            }

            if (clazz.getName().equals(TimeFilter.class.getName())
                    || clazz.getName().equals(DateFilter.class.getName())
                    || clazz.getName().equals(SystemsFilter.class.getName())
                    || clazz.getName().equals(ParametersFilter.class.getName())
                    || clazz.getName().equals(KeywordsFilter.class.getName())
                    || clazz.getName().equals(BehaviorFilter.class.getName())
                    || clazz.getName().equals(GeoFilter.class.getName())
                    || clazz.getName().equals(TrafficFilter.class.getName())
                    || clazz.getName().equals(ContentFilter.class.getName())) {
                if (entity == null) {
                    adserverModel.removeFilter(clazz.getName(), id);
                } else {
                    adserverModel.updateFilter(clazz.getName(), entity);
                }

                return;
            }

            if (clazz.getName().equals(DynamicParameter.class.getName())) {
                if (entity == null) {
                    adserverModel.removeDynamicParameter(id);
                } else {
                    adserverModel
                            .updateDynamicParameter((DynamicParameter) entity);
                }

                return;
            }

            if (clazz.getName().equals(KeyWordsFilterElement.class.getName())) {
                if (entity == null) {
                    adserverModel.removeKeyWordsFilterElement(id);
                } else {
                    adserverModel
                            .updateKeyWordsFilterElement((KeyWordsFilterElement) entity);
                }

                return;
            }

            if (clazz.getName().equals(ParameterImpl.class.getName())) {
                if (entity == null) {
                    adserverModel.getParametersMap().remove(id);
                } else {
                    adserverModel.getParametersMap().put(id, entity);
                }

                return;
            }

            logger.info("AdserverModel update took:"
                    + (System.currentTimeMillis() - t1));
        } catch (Exception ex) {
            logger.error("", ex);
        }
    }

    private boolean removeDefaultPublisherBanner(
            Map<Integer, Map<Integer, IMappable>> pool, Integer Id) {
        for (Map<Integer, IMappable> map : pool.values())
            for (IMappable im : map.values())
                if (((BannerImpl) im).getId().equals(Id)) {
                    map.remove(im);

                    return true;
                }

        return false;
    }

    public void updateSitesAndKeywords(KeywordsRealtimeImpl krObj) {
        adserverModel.getSitePagesAndKeywords().put(krObj.getPublisherUrl(),
                krObj.getRealTimeKeyWords());
    }

    private Map<Integer, IMappable> generateMapFromList(List<IMappable> l) {
        Map<Integer, IMappable> m = new HashMap<Integer, IMappable>();
        for (IMappable im : l) {
            m.put(im.getId(), im);
        }
        return m;
    }

    private void removeTotalsReport(Class cl, Integer id) {
        String key = id.toString() + cl.getName();
        totalsReporterModel.getEntityObjects().remove(key);
        Collection c = hibernateEntityDao.viewWithCriteria(TotalsReport.class, new Object[]{"entityid", id, "entityclass", cl.getName()});
        if (c != null && c.size() >= 1) {
            TotalsReport tr = (TotalsReport) c.toArray()[0];
            hibernateEntityDao.removeObject(tr);
        }
    }

    public HibernateEntityDao getHibernateEntityDao() {
        return hibernateEntityDao;
    }

    public void setHibernateEntityDao(HibernateEntityDao hibernateEntityDao) {
        this.hibernateEntityDao = hibernateEntityDao;
    }

    public AdserverModel getAdserverModel() {
        return adserverModel;
    }

    public void setAdserverModel(AdserverModel adserverModel) {
        this.adserverModel = adserverModel;
    }

    public BannerManagementService getBannerManagementService() {
        return bannerManagementService;
    }

    public void setBannerManagementService(
            BannerManagementService bannerManagementService) {
        this.bannerManagementService = bannerManagementService;
    }

    public TotalsReporterModel getTotalsReporterModel() {
        return totalsReporterModel;
    }

    public void setTotalsReporterModel(TotalsReporterModel totalsReporterModel) {
        this.totalsReporterModel = totalsReporterModel;
    }
}
