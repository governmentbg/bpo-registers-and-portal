import { ReactComponent as MarkLogo } from "@duosoftbg/bpo-components/dist/assets/images/objects/icon-mark.svg";
import { ReactComponent as GILogo } from "@duosoftbg/bpo-components/dist/assets/images/objects/icon-geo.svg";
import { ReactComponent as PatentLogo } from "@duosoftbg/bpo-components/dist/assets/images/objects/icon-patent.svg";
import { ReactComponent as EuPatentLogo } from "@duosoftbg/bpo-components/dist/assets/images/objects/icon-eupatent.svg";
import { ReactComponent as UMLogo } from "@duosoftbg/bpo-components/dist/assets/images/objects/icon-utility-model.svg";
import { ReactComponent as DogLogo } from "@duosoftbg/bpo-components/dist/assets/images/objects/icon-dog.svg";
import { ReactComponent as SPCLogo } from "@duosoftbg/bpo-components/dist/assets/images/objects/icon-pills.svg";
import { ReactComponent as TopologyLogo } from "@duosoftbg/bpo-components/dist/assets/images/objects/icon-chip.svg";
import { ReactComponent as DesignLogo } from "@duosoftbg/bpo-components/dist/assets/images/objects/icon-design.svg";
import { ReactComponent as AgentLogo } from "@duosoftbg/bpo-components/dist/assets/images/objects/icon-agent.svg";
import { ReactComponent as AssociationLogo } from "@duosoftbg/bpo-components/dist/assets/images/objects/icon-association.svg";
import { ReactComponent as DecisionLogo } from "@duosoftbg/bpo-components/dist/assets/images/objects/icon-law.svg";
import { ReactComponent as CombineSearchLogo } from "@duosoftbg/bpo-components/dist/assets/images/objects/icon-search-3.svg";
import { ProcessEnvironments, SidebarData, SvgIcon } from "@duosoftbg/bpo-components";
import { messagesBg, messagesEn } from "../../i18n";

const registersCardList = [
  {
    id: "marks",
    href: "/marks",
    icon: MarkLogo,
    component: <MarkLogo />,
    title: "t.marks",
    titleEn: "t.marks",
    description: "m.registers.marks.desc",
  },
  {
    id: "geo-indications",
    href: "/geo-indications",
    icon: GILogo,
    component: <GILogo />,
    title: "t.geoIndications",
    titleEn: "t.geoIndications",
    description: "m.registers.geoIndications.desc",
  },

  {
    id: "patents",
    href: "/patents",
    icon: PatentLogo,
    component: <PatentLogo />,
    title: "t.nationalPatents",
    titleEn: "t.nationalPatents",
    description: "m.registers.nationalPatents.desc",
  },
  {
    id: "eu-patents",
    href: "/eu-patents",
    icon: EuPatentLogo,
    component: <EuPatentLogo />,
    title: "t.epoPatents",
    titleEn: "t.epoPatents",
    description: "m.registers.epoPatents.desc",
  },
  {
    id: "utility-models",
    href: "/utility-models",
    icon: UMLogo,
    component: <UMLogo />,
    title: "t.utilityModels",
    titleEn: "t.utilityModels",
    description: "m.registers.utilityModels.desc",
  },
  {
    id: "plants-breeds",
    href: "/plants-breeds",
    icon: DogLogo,
    component: <DogLogo />,
    title: "t.plantsBreeds",
    titleEn: "t.plantsBreeds",
    description: "m.registers.plantsBreeds.desc",
  },
  {
    id: "spc",
    href: "/spc",
    icon: SPCLogo,
    component: <SPCLogo />,
    title: "t.spc",
    titleEn: "t.spc",
    description: "m.registers.spc.desc",
  },
  {
    id: "topologies",
    href: "/topologies",
    icon: TopologyLogo,
    component: <TopologyLogo />,
    title: "t.topology",
    titleEn: "t.topology",
    description: "m.registers.topology.desc",
  },
  {
    id: "designs",
    href: "/designs",
    icon: DesignLogo,
    component: <DesignLogo />,
    title: "t.designs",
    titleEn: "t.designs",
    description: "m.registers.designs.desc",
  },
  {
    id: "agents",
    href: "/agents",
    icon: AgentLogo,
    component: <AgentLogo />,
    title: "t.agents",
    titleEn: "t.agents",
    description: "m.registers.agents.desc",
  },
  {
    id: "partnerships",
    href: "/partnerships",
    icon: AssociationLogo,
    component: <AssociationLogo />,
    title: "t.associations",
    titleEn: "t.associations",
    description: "m.registers.associations.desc",
  },
  {
    id: "legal-decisions",
    href: "/legal-decisions",
    icon: DecisionLogo,
    component: <DecisionLogo />,
    title: "t.decisions",
    titleEn: "t.decisions",
    description: "m.registers.decisions.desc",
  },
  {
    id: "combined-search",
    href: "/combined-search",
    icon: CombineSearchLogo,
    component: <CombineSearchLogo />,
    title: "t.combineSearch",
    titleEn: "t.combineSearch",
    description: "m.registers.combineSearch.desc",
  },
];

export const staticSidebarData: SidebarData[] = [
  {
    id: "backoffice-sidebar",
    title: "",
    titleEn: "",
    pages: [
      {
        id: "marks",
        href: `${ProcessEnvironments.PublicUrl}/marks`,
        icon: MarkLogo,
        title: messagesBg.translation["t.marks"],
        titleEn: messagesEn.translation["t.marks"],
      },
      {
        id: "geo-indications",
        href: `${ProcessEnvironments.PublicUrl}/geo-indications`,
        icon: GILogo,
        title: messagesBg.translation["t.geoIndications"],
        titleEn: messagesEn.translation["t.geoIndications"],
      },
      {
        id: "patents",
        href: `${ProcessEnvironments.PublicUrl}/patents`,
        icon: PatentLogo,
        title: messagesBg.translation["t.nationalPatents"],
        titleEn: messagesEn.translation["t.nationalPatents"],
      },
      {
        id: "eu-patents",
        href: `${ProcessEnvironments.PublicUrl}/eu-patents`,
        icon: EuPatentLogo,
        title: messagesBg.translation["t.epoPatents"],
        titleEn: messagesEn.translation["t.epoPatents"],
      },

      {
        id: "utility-models",
        href: `${ProcessEnvironments.PublicUrl}/utility-models`,
        icon: UMLogo,
        title: messagesBg.translation["t.utilityModels"],
        titleEn: messagesEn.translation["t.utilityModels"],
      },
      {
        id: "plants-breeds",
        href: `${ProcessEnvironments.PublicUrl}/plants-breeds`,
        icon: DogLogo,
        title: messagesBg.translation["t.plantsBreeds"],
        titleEn: messagesEn.translation["t.plantsBreeds"],
      },
      {
        id: "spc",
        href: `${ProcessEnvironments.PublicUrl}/spc`,
        icon: SPCLogo,
        title: messagesBg.translation["t.spc"],
        titleEn: messagesEn.translation["t.spc"],
      },
      {
        id: "designs",
        href: `${ProcessEnvironments.PublicUrl}/designs`,
        icon: DesignLogo,
        title: messagesBg.translation["t.designs"],
        titleEn: messagesEn.translation["t.designs"],
      },
      {
        id: "topologies",
        href: `${ProcessEnvironments.PublicUrl}/topologies`,
        icon: TopologyLogo,
        title: messagesBg.translation["t.topologies"],
        titleEn: messagesEn.translation["t.topologies"],
      },
      {
        id: "agents",
        href: `${ProcessEnvironments.PublicUrl}/agents`,
        icon: AgentLogo,
        title: messagesBg.translation["t.agents"],
        titleEn: messagesEn.translation["t.agents"],
      },
      {
        id: "partnerships",
        href: `${ProcessEnvironments.PublicUrl}/partnerships`,
        icon: AssociationLogo,
        title: messagesBg.translation["t.associations"],
        titleEn: messagesEn.translation["t.associations"],
      },
      {
        id: "legal-decisions",
        href: `${ProcessEnvironments.PublicUrl}/legal-decisions`,
        icon: DecisionLogo,
        title: messagesBg.translation["t.decisions"],
        titleEn: messagesEn.translation["t.decisions"],
      },
      {
        id: "combined-search",
        href: `${ProcessEnvironments.PublicUrl}/combined-search`,
        icon: CombineSearchLogo,
        title: messagesBg.translation["t.combineSearch"],
        titleEn: messagesEn.translation["t.combineSearch"],
      },
    ],
  },
];

export const dynamicSidebarData = (data): SidebarData[] => [
  {
    id: "backoffice-sidebar",
    title: "",
    titleEn: "",
    pages: data.data.childrenPanels.map((panel: any) => ({
      id: panel.id,
      title: panel.titleBg,
      titleEn: panel.titleEn,
      href: panel.action,
      icon: () => <SvgIcon img={panel.img} />,
    })),
  },
];

const navItems = [
  {
    title: "t.eRegisters",
    pages: registersCardList,
  },
];

export { registersCardList };
export default navItems;
