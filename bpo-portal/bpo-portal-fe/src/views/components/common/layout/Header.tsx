import * as React from "react";
import { BpoHeader } from "@duosoftbg/bpo-components";

type HeaderProps = {
  hideSubHeader?: boolean;
};

const Header = ({ hideSubHeader = false }: HeaderProps) => {
  return <BpoHeader showMonitoring={false} hideSubHeader={hideSubHeader} />;
};
export default Header;
