export declare type SectionContentType = {
  id: string;
  labelBg: string;
  labelEn: string;
  descriptionBg?: string;
  descriptionEn?: string;
  childrenPanels: SectionChildType[];
};

export declare type SectionChildType = {
  id: string;
  titleBg: string;
  titleEn: string;
  descriptionBg?: string;
  descriptionEn?: string;
  action: string;
  actionTarget: "_self" | "_blank";
  img?: string;
};

export interface HomePageType {
  descriptionBg: string;
  descriptionEn: string;
  sections: SectionContentType[];
}
