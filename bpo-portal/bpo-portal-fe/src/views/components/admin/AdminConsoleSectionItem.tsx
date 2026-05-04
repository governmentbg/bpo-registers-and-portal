import { BoxSpg, iTxt, SecurityGuard, useRedirect } from "@duosoftbg/bpo-components/";
import React, { Fragment } from "react";
import { getI18n } from "react-i18next";
import styled from "styled-components";
import { doesExistUrl } from "../../../utils/sidebar";
import { adminConsoleStyles } from "../../../utils/constants";

const ItemTxt = styled.a<{
  deepNumber: number;
  haveUrl: boolean;
}>`
  font-weight: bold;
  color: #4679b2;
  margin-bottom: 25px;
  margin-top: 25px;
  ${(props) =>
    `font-size: ${
      adminConsoleStyles.fontStartSize - props.deepNumber * adminConsoleStyles.fontSizeStep >
      adminConsoleStyles.fontMinSize
        ? adminConsoleStyles.fontStartSize - props.deepNumber * adminConsoleStyles.fontSizeStep
        : adminConsoleStyles.fontMinSize
    }px;`};
  ${(props) =>
    "margin-left:" +
    (props.deepNumber * adminConsoleStyles.marginLeftStep < adminConsoleStyles.marginLeftMax
      ? props.deepNumber * adminConsoleStyles.marginLeftStep
      : adminConsoleStyles.marginLeftMax
    ).toString() +
    "px;"};
  ${(props) => props.haveUrl && ":hover {  text-decoration: underline;  cursor: pointer;} "}
  :before {
    ${(props) => props.deepNumber === 1 && 'content: "•";'}
    ${(props) => props.deepNumber === 2 && 'content: "◦";'}
    ${(props) => props.deepNumber === 3 && 'content: "›";'}
    ${(props) => props.deepNumber === 4 && 'content: "»";'}
    ${(props) => props.deepNumber === 5 && 'content: "-";'}
    ${(props) => props.deepNumber > 5 && 'content: "~";'}
  }
`;

const AdminConsoleSectionItem = ({ item }) => {
  const deepNumber = 1;
  const accessRoles = item.accessRoles;
  if (accessRoles && accessRoles.length > 0) {
    return (
      <SecurityGuard checkForRoles={accessRoles} roleOperator={"or"}>
        <BoxSpg key={item.id}>
          <AdminConsoleSectionItemContent deepNumber={deepNumber} item={item} />
        </BoxSpg>
      </SecurityGuard>
    );
  }

  return (
    <BoxSpg>
      <AdminConsoleSectionItemContent item={item} deepNumber={deepNumber} />
    </BoxSpg>
  );
};

const AdminConsoleSectionItemContent = ({ item, deepNumber }) => {
  const { redirect } = useRedirect();
  const isLink = doesExistUrl(item);

  return (
    <Fragment>
      {isLink ? (
        <ItemTxt deepNumber={deepNumber++} haveUrl={isLink} onClick={() => redirect(item.url)}>
          {iTxt(getI18n().language, item.title, item.titleEn)}
        </ItemTxt>
      ) : (
        <ItemTxt deepNumber={deepNumber++} haveUrl={isLink}>
          {iTxt(getI18n().language, item.title, item.titleEn)}
        </ItemTxt>
      )}
      {item.children?.map((item) => {
        if (item?.accessRoles && item?.accessRoles.length > 0) {
          return (
            <SecurityGuard checkForRoles={item?.accessRoles} roleOperator={"or"} key={item.id}>
              <BoxSpg>
                <AdminConsoleSectionItemContent item={item} deepNumber={deepNumber} />
              </BoxSpg>
            </SecurityGuard>
          );
        }
        return (
          <BoxSpg>
            <AdminConsoleSectionItemContent item={item} deepNumber={deepNumber} />
          </BoxSpg>
        );
      })}
    </Fragment>
  );
};

export default AdminConsoleSectionItem;
