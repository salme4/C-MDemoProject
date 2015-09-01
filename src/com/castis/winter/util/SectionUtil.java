package com.castis.winter.util;

import org.davic.mpeg.sections.EndOfFilteringEvent;
import org.davic.mpeg.sections.IncompleteFilteringEvent;
import org.davic.mpeg.sections.RingSectionFilter;
import org.davic.mpeg.sections.Section;
import org.davic.mpeg.sections.SectionAvailableEvent;
import org.davic.mpeg.sections.SectionFilterEvent;
import org.davic.mpeg.sections.SectionFilterGroup;
import org.davic.mpeg.sections.SectionFilterListener;
import org.davic.mpeg.sections.VersionChangeDetectedEvent;
import org.davic.resources.ResourceClient;
import org.davic.resources.ResourceProxy;


public class SectionUtil implements SectionFilterListener, ResourceClient {
    private static final long FILTERING_TIMEOUT = 90000L;

    private int GROUP_TABLE_ID = 201;

    private SectionFilterGroup sfGroup;

    private RingSectionFilter filter = null;

    private boolean isStarted = false;

    private boolean isProcessed = false;

    // TODO : -1로 설정해야한다.
    int mapId = -1;

    public int getMapId() {
	return mapId;
    }

    public void setMapId(int mapId) {
	this.mapId = mapId;
    }

    public SectionUtil() {
	sfGroup = new SectionFilterGroup(1);
    }

    public void start() {
	Logger.I(this, "SectionUtil.start()");
	try {
	    if (!isStarted) {
		isStarted = true;
		sfGroup.attach(org.ocap.mpeg.PODExtendedChannel.getInstance(), this, null);
		startFiltering(GROUP_TABLE_ID, 1);
	    }
	}catch (Exception e) {
	    destroy();
	}

    }

    private void startFiltering(int tableId, int sectionSize) {
	Logger.I(this, "startFiltering(" + tableId + ", " + sectionSize + ")");
	filter = sfGroup.newRingSectionFilter(10);
	try {
	    filter.addSectionFilterListener(this);

	    filter.setTimeOut(FILTERING_TIMEOUT);
	    filter.startFiltering(null, 8188, tableId);// PID = 0x1FFC T_ID=0x11
	} catch (Exception e) {
	    e.printStackTrace();
	    destroy();
	}
    }

    private void stopFiltering() {
	if (filter != null) {
	    filter.stopFiltering();
	    filter.removeSectionFilterListener(this);
	}
    }

    public void destroy() {
	try {
	    isProcessed = false;
	    isStarted = false;
	    if (filter != null) {
		stopFiltering();
		filter.removeSectionFilterListener(this);
		sfGroup.detach();
		filter = null;
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void parseSection(Section[] sections) {
	Logger.I(this, "parseSection()");
	if (sections == null || sections.length == 0) {
	    Logger.I(this, "parseSection() : received Sections are null!");
	    return;
	}
	int tableId = 0;
	for (int i = 0; i < sections.length; i++) {
	    if (sections[i] != null) {
		try {
		    tableId = sections[i].table_id();
		    Logger.I(this, "Section's tableId = " + tableId);
		} catch (Exception e) {
		    e.printStackTrace();
		}
		break;
	    }
	}
	Logger.I(this, "Table ID of Section = " + tableId);
	if (tableId == GROUP_TABLE_ID) {
	    parseGroup(sections);
	}
    }

    private int parseGroup(Section[] sections) {
	Logger.I(this, "parseGroup()");
	try {
	    for (int i = 0; i < sections.length; i++) {
		if (sections[i] == null || sections[i].getData() == null) {
		    continue;
		}
		int tableId = sections[i].table_id();
		if (tableId == GROUP_TABLE_ID) {
		    Logger.I(this, "Group Table Section!!!");
		    byte[] data2 = sections[i].getData(4, 2);
		    mapId = (data2[0] & (int) 0xFF) << 8 | (data2[1] & (int) 0xFF);
		    Logger.I(this, "result = " + mapId);
		    destroy();
		    break;
		}
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	    destroy();
	}
	return GROUP_TABLE_ID;
    }

    public void sectionFilterUpdate(SectionFilterEvent event) {
	if (event instanceof SectionAvailableEvent) {
	    if (!isProcessed) {
		isProcessed = true;
		Logger.I(this, "SectionAvailableEvent" + filter.getSections().length);
		RingSectionFilter filter = (RingSectionFilter) event.getSource();
		parseSection(filter.getSections());
	    }
	} else if (event instanceof VersionChangeDetectedEvent) {
	} else if (event instanceof IncompleteFilteringEvent) {
	} else if (event instanceof EndOfFilteringEvent) {
	    Logger.I(this, "SectionFilterUpdated : EndOfFilteringEvent received");
	}
    }

    public void notifyRelease(ResourceProxy arg0) {
    }

    public void release(ResourceProxy arg0) {
    }

    public boolean requestRelease(ResourceProxy arg0, Object arg1) {
	return false;
    }
}
