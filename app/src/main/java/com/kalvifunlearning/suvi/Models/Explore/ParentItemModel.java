package com.kalvifunlearning.suvi.Models.Explore;

import java.util.List;

public class ParentItemModel {
    private String ParentItemTitle;
    private List<ChildItemModel> ChildItemList;

    // Constructor of the class
    // to initialize the variables
    public ParentItemModel(String ParentItemTitle, List<ChildItemModel> ChildItemList)
    {
        this.ParentItemTitle = ParentItemTitle;
        this.ChildItemList = ChildItemList;
    }

    // Getter and Setter methods
    // for each parameter
    public String getParentItemTitle()
    {
        return ParentItemTitle;
    }

    public void setParentItemTitle(String parentItemTitle)
    {
        ParentItemTitle = parentItemTitle;
    }

    public List<ChildItemModel> getChildItemList()
    {
        return ChildItemList;
    }

    public void setChildItemList(List<ChildItemModel> childItemList)
    {
        ChildItemList = childItemList;
    }
}
